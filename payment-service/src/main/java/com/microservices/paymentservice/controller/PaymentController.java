package com.microservices.paymentservice.controller;

import com.microservices.paymentservice.entity.Payment;
import com.microservices.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment){
        return paymentService.doThePayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderID(@PathVariable int orderId){
        return paymentService.findPaymentByOrderID(orderId);
    }
}
