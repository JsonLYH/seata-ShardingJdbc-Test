package org.example.controller;

import org.example.model.Order;
import org.example.service.OrderService;
import org.example.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("createOrder")
    public ResultUtils createOrder(@RequestParam String userId,@RequestParam String commodityCode,@RequestParam Long count,@RequestParam Long productMoney){
        Order order= orderService.createOrder(userId,commodityCode,count,productMoney);
        ResultUtils<Order> resultUtils = ResultUtils.success("创建订单成功",order);
        return resultUtils;
    }
}
