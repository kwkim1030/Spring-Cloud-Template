package com.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final Environment environment;
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/info")
    public String info(@Value("${server.port}") String port) {

        logger.info("123123");
        return "Order 서비스의 기본 동작 Port: {" + port + "} " + environment.getProperty("greeting");
    }

    @PostMapping("/{userId}/orders")
    public String getOrders(@PathVariable("userId") Long id) {
        try {
            logger.warn("Circuit Breaker Test id : {}", id);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = id + " 주문 내역 @@@";
        return res;
    }
}
