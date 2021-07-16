package com.dc.ssjs.controller;

import com.dc.ssjs.entity.Order;
import com.dc.ssjs.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器
 *
 * @author duchao
 */
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public void add(@RequestBody Order order) {
        orderService.save(order);
    }

    @GetMapping("/orders")
    public List<Order> selectList() {
        return orderService.list();
    }
}
