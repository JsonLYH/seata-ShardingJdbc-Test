package org.example.service.impl;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.example.mapper.StorageMapper;
import org.example.model.Storage;
import org.example.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

    @Resource
    private StorageMapper storageMapper;

    /**
     * 库存扣减
     * @param commodityCode
     * @param count
     */
    @Override
    //这个注解可以不加
//    @ShardingTransactionType(TransactionType.BASE)
    public void deduct(Long commodityCode, Long count) throws TransactionException {
        LOGGER.info("Stock Service Begin ... xid: " + RootContext.getXID());
        LOGGER.info("Deducting inventory SQL: update stock_tbl set count = count - {} where commodity_code = {}", count,
                commodityCode);
        Storage stock = storageMapper.findByCommodityCode(commodityCode);
        if(stock.getCount() - count < 0){
            throw new RuntimeException("当前商品库存不足");
        }
        stock.setCount(stock.getCount() - count);
        storageMapper.updateById(stock);
        LOGGER.info("Stock Service End ... ");

    }

}
