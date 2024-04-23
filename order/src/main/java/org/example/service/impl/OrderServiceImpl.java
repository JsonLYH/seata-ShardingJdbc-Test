package org.example.service.impl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.example.feign.AccountFeign;
import org.example.feign.StorageFeign;
import org.example.mapper.OrderMapper;
import org.example.model.Order;
import org.example.service.OrderService;
import org.example.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private AccountFeign accountFeign;
    @Autowired
    private StorageFeign storageFeign;

    @Resource
    private OrderMapper orderMapper;

    @Override
    @Transactional
    //这个是ShardingJdbc服务，所以入口标记使用@ShardingTransactionType
    //如果是普通的服务，则使用@GlobalTransactional
    @ShardingTransactionType(TransactionType.BASE)
    public Order createOrder(String userId, String commodityCode, Long count,Long productMoney) {
        LOGGER.info("Order Service Begin ... xid: " + RootContext.getXID());
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        Long orderMoney = calculate(productMoney, count);
        order.setMoney(orderMoney);
        //订单插入
        orderMapper.insert(order);
        //远程调用-查询账户余额
        Long currentMoney = accountFeign.getMoney(userId);
        if(currentMoney-orderMoney<0){
            throw new RuntimeException("当前账户余额不足");
        }
        //远程调用,进行库存扣减
        ResultUtils resultUtils = storageFeign.deduct(commodityCode, count);
        //远程调用,进行金额扣减
        accountFeign.debit(userId, currentMoney-orderMoney);

        return order;
    }

    private Long calculate(Long productMoney, Long count) {
        return productMoney * count;
    }

}
