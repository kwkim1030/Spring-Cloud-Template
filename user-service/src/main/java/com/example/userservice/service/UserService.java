package com.example.userservice.service;

import com.example.userservice.client.OrderClient;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {

    private final OrderClient orderClient;
    private CircuitBreakerFactory circuitBreakerFactory;

    public UserService(OrderClient orderClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.orderClient = orderClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public String getUser(@PathVariable("userId") Long id) {
        String res = "";
        String user = id + " 유저정보 @@@@";
        //String orders = orderClient.getOrders(id);

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("order");
        String orders = circuitBreaker.run(()->
                orderClient.getOrders(id), throwable -> "fallback" );

        res = user + "\n" + orders;
        return res;
    }
}
