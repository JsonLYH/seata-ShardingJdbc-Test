package org.example.service;

public interface AccountService {
    /**
     * 从用户账户中借出
     */
    void debit(String userId, Long money);
    Long getMoney(String userId);
}
