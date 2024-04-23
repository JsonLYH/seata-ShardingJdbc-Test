package org.example.service.impl;

import io.seata.core.context.RootContext;
import org.example.mapper.AccountMapper;
import org.example.model.Account;
import org.example.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Resource
    private AccountMapper accountMapper;

    /**
     * 根据用户id，设置当前用户金额
     * @param userId
     * @param money
     */
    @Override
    public void debit(String userId, Long money) {
        LOGGER.info("Account Service ... xid: " + RootContext.getXID());
        LOGGER.info("Deducting balance SQL: update account_tbl set money = money - {} where user_id = {}", money,
                userId);
        Account account = accountMapper.selectByUserId(userId);
        account.setMoney(money);
        accountMapper.updateById(account);
        LOGGER.info("Account Service End ... ");
    }

    /**
     * 查询当前账户余额
     * @param userId
     * @return
     */
    @Override
    public Long getMoney(String userId) {
        LOGGER.info("Order Service Begin ... xid: " + RootContext.getXID());
        Account account=accountMapper.selectByUserId(userId);
        if(account == null){
            throw new RuntimeException("不存在当前账户");
        }
        return account.getMoney();
    }
}
