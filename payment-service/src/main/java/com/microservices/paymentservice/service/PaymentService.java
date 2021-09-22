package com.microservices.paymentservice.service;

import com.microservices.paymentservice.entity.Payment;
import com.microservices.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doThePayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        return new Random().nextBoolean()?"Success":"Failure";
    }

    public Payment findPaymentByOrderID(int orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
