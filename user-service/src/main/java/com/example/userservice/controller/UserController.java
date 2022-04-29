package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class UserController {
    private final Environment environment;
    private final UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/info")
    public String info(@Value("${server.port}") String port) {

        logger.info("123123");
        return "User 서비스의 기본 동작 Port: {" + port + "} " + environment.getProperty("greeting");
    }

    @GetMapping("/auth")
    public String auth(@RequestHeader(value = "token") String token) {
        return "token is " + token;
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") Long id) {
        String message = userService.getUser(id);
        return message;
    }

}
