package com.oracle.xing.spi;

import com.oracle.xing.selfwired.Oracle;
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
    @Oracle(values = {Oracle.ADMIN,Oracle.NORMAL,Oracle.SUPER_ADMIN})
    public String getTopic() {
        return "PAY_MONEY";
    }
}
