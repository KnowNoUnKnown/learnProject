package com.oracle.xing.controller;

import com.alibaba.fastjson.JSON;
import com.oracle.xing.model.User;
import com.oracle.xing.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/10/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @GetMapping("/get")
    public String getUser(@RequestParam("id") String id){
        User user = userServer.getUser(id);
        return JSON.toJSONString(user);
    }
}
