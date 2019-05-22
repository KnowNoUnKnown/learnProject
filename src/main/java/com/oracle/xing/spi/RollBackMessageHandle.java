package com.oracle.xing.spi;

import org.springframework.stereotype.Component;

/**
 * Created by liuyong
 * 2018-12-06  14-29
 */
@Component
public class RollBackMessageHandle implements MessageHandle {

    @Override
    public void execute() {
        System.out.println("退款消息处理!======"+getTopic());
    }

    @Override
    public String getTopic() {
        return "ROOL_BACK";
    }
}
