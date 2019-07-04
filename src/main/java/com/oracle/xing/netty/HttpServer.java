package com.oracle.xing.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Created by liuyong
 * 2018-11-07  14-48
 */

public class HttpServer {

    private Integer port;

    public HttpServer(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public static void main(String []args){
        new HttpServer(10000).start();
    }

    public void start(){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
        serverBootstrap.group(boosGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChildHandler())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
                ChannelFuture future = serverBootstrap.bind(port).sync();
                future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     *
     */
    class ChildHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(
                    new HttpServerCodec(),
                    new HttpObjectAggregator(1024*1024),
                    new ChannelInboundHandler());
        }
    }
}
