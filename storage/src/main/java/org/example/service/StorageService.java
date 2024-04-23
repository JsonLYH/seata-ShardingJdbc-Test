package org.example.service;

import io.seata.core.exception.TransactionException;

public interface StorageService {
    /**
     * 扣除存储数量
     */
    void deduct(Long commodityCode, Long count) throws TransactionException;
}
