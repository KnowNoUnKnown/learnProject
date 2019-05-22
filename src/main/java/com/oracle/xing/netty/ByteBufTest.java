package com.oracle.xing.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.io.FileInputStream;

/**
 * Created by liuyong
 * 2018-12-12  10-17
 */

public class ByteBufTest {

    public static void main(String[] args){


        Channel channel = null;

        channel.alloc().buffer();
        /**
         * 非池化创建
         */
        ByteBuf byteBuf = Unpooled.buffer(1024);
        /**
         * 池化创建
         */
        byteBuf = ByteBufAllocator.DEFAULT.buffer();
    }

}