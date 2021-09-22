package com.microservices.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/orderFallback")
    public Mono<String> orderServiceFallback(){
        return Mono.just("order service is taking too long or is down, please try again later");
    }

    @GetMapping("/paymentService")
    public Mono<String> paymentServiceFallback(){
        return Mono.just("Payment Service is taking too long or is down, please try again later");
    }
}
