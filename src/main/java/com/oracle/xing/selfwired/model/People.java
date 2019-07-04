package com.oracle.xing.selfwired.model;

import com.oracle.xing.selfwired.annotation.Controller;
import com.oracle.xing.selfwired.annotation.Request;

/**
 * https://github.com/oraclexing
 * <p>
 * 自定义注解
 *
 * @author stardust
 * @version 1.0.0
 */
@Controller(url = "/people")
public class People {

    @Request(url = "user/login",type = Request.GET)
    public void save(String age,String param){
        System.out.println(age+param);
    }
}