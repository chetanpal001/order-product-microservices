package com.microservices.orderservice.service;

import com.microservices.orderservice.common.Payment;
import com.microservices.orderservice.common.TransactionRequest;
import com.microservices.orderservice.common.TransactionResponse;
import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request){
        String message;
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        //Rest call to Payment
        Payment paymentResponse=restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);
        message=paymentResponse.getPaymentStatus().equals("Success")?"Payment Processed Successfully":"Payment Failure";
        orderRepository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),message);
    }
}
