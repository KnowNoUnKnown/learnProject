package com.oracle.xing.selfwired;

import com.oracle.xing.selfwired.annotation.Controller;
import com.oracle.xing.selfwired.annotation.Request;
import com.oracle.xing.selfwired.model.People;

import java.lang.reflect.Method;

/**
 * https://github.com/oraclexing
 * <p>
 * 自定义注解
 *
 *
 * @author stardust
 * @version 1.0.0
 */
public class WiredTest {

    public static void main(String...args)throws Exception{
        Class clazz = Class.forName(People.class.getName());
        if(clazz.isAnnotationPresent(Controller.class)){
            // 该类已经被Controller.class 修饰
            Controller controller = (Controller) clazz.getAnnotation(Controller.class);
            System.out.println("类上面的注解url："+controller.url());
            for(Method method : clazz.getDeclaredMethods()){
                Request request = method.getAnnotation(Request.class);
                if(null == controller){
                    continue;
                }
                System.out.println(method.getName()+"=====>"+request.url());
                System.out.println(method.getName()+"=====>"+request.type());
            }
        }




    }
}