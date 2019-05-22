package com.oracle.xing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/10/15.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "Hello , Spring Boot";
    }
}
