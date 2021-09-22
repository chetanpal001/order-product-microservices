package com.microservices.orderservice.controller;

import com.microservices.orderservice.common.Payment;
import com.microservices.orderservice.common.TransactionRequest;
import com.microservices.orderservice.common.TransactionResponse;
import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return orderService.saveOrder(request);

    }
}
