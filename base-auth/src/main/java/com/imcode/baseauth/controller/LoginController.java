package com.imcode.baseauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {


    @GetMapping("/echo")
    public Object echo( String str) throws InterruptedException {

        log.info("get msg :{}" , str );
        Thread.sleep(6000);

        return str;
    }
}
