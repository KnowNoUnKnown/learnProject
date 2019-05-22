package com.oracle.xing.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

/**
 * Created by liuyong
 * 2018-11-07  14-48
 */

public class HttpNetty {

    private Integer port;

    public HttpNetty(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public static void main(String []args)throws Exception{
        new HttpNetty(10000).start();
    }

    public void start(){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
        serverBootstrap.group(boosGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new DiscardServerHandler());
                        /**
                         *   ch.pipeline()
                         *   .addLast("handler",new HttpHandler());
                         *
                         */
                    }
                }).option(ChannelOption.SO_BACKLOG, 128) // determining the number of connections queued
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
}
