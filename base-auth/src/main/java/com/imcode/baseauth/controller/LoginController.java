package com.imcode.baseauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/login")
@Slf4j
public class LoginController {


    @GetMapping("auth/echo")
    public Object echo( String str){

        log.info("get msg :{}" , str );

        return str;
    }
}
