package org.example.service;

import org.example.model.Order;

public interface OrderService {
    /**
     * 创建订单
     */
    Order createOrder(String userId, String commodityCode, Long orderCount,Long productMoney);
}
