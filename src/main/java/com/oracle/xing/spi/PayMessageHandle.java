package com.oracle.xing.spi;

import org.springframework.stereotype.Component;

/**
 * Created by liuyong
 * 2018-12-06  14-28
 */
@Component
public class PayMessageHandle implements MessageHandle {

    @Override
    public void execute() {
        System.out.println("用户已经支付!======="+getTopic());
    }

    @Override
    public String getTopic() {
        return "PAY_MONEY";
    }
}
