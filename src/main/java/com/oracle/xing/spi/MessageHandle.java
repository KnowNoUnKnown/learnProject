package com.oracle.xing.spi;

/**
 * Created by liuyong
 * 2018-12-06  14-24
 */

public interface MessageHandle {

    /**
     * 执行业务逻辑
     */
    void execute();

    /**
     * 获取topic
     * @return
     */
    String getTopic();

}
