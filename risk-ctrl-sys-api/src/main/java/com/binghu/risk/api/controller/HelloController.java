package com.binghu.risk.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @PostMapping("/test")
    public String testHello() {
        return "hello from risk ctrl";
    }

}
