package com.oracle.xing.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuyong
 * 2018-12-12  14-31
 */

public class DiscardServerHandler extends ChannelInboundHandlerAdapter{

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ByteBuf buf;

    /**
     * 连接被创建时,调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        buf = ctx.alloc().buffer();
        super.channelActive(ctx);
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(simpleDateFormat.format(new Date()).getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    /**
     * 接收到消息,调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf byteBuf = (ByteBuf) msg;
      if(13!=byteBuf.readByte()){
          buf.writeBytes(byteBuf);
      }else{
        System.out.println(buf.toString(io.netty.util.CharsetUtil.US_ASCII));
        ctx.writeAndFlush(buf);
      }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
