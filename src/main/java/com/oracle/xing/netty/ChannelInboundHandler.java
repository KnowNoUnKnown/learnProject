package com.oracle.xing.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


/**
 * https://github.com/oraclexing
 * <p>
 *
 * @author stardust
 * @version 1.0.0
 *
 */
public class ChannelInboundHandler extends SimpleChannelInboundHandler<HttpObject> {

    private final Logger logger = LoggerFactory.getLogger(ChannelInboundHandler.class);

    private HttpHeaders headers;
    private HttpRequest request;
    private FullHttpRequest fullRequest;

    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest){
            request = (HttpRequest) msg;
            headers = request.headers();
            String uri = request.uri();
            logger.info("http uri: "+ uri);
            HttpMethod method = request.method();
            if (method.equals(HttpMethod.GET)){
                QueryStringDecoder queryDecoder = new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8));
                Map<String, List<String>> uriAttributes = queryDecoder.parameters();
                //此处仅打印请求参数（你可以根据业务需求自定义处理）
                for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                    for (String attrVal : attr.getValue()) {
                        logger.info(attr.getKey() + "=" + attrVal);
                    }
                }
            }else if (method.equals(HttpMethod.POST)){
                //POST请求,由于你需要从消息体中获取数据,因此有必要把msg转换成FullHttpRequest
                fullRequest = (FullHttpRequest)msg;
                //根据不同的Content_Type处理body数据
                dealWithContentType();
            }

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(("你好世界，"+uri).getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "text/html;charset=utf-8");
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (!keepAlive) {
                channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                channelHandlerContext.writeAndFlush(response);
            }
        }
    }

    /**
     * 简单处理常用几种 Content-Type 的 POST 内容（可自行扩展）
     * @throws Exception
     */
    private void dealWithContentType() throws Exception{
        String contentType = getContentType();
        //可以使用HttpJsonDecoder
        if(contentType.equals("application/json")){
            String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            JSONObject obj = JSON.parseObject(jsonStr);
            for(Map.Entry<String, Object> item : obj.entrySet()){
                logger.info(item.getKey()+"="+item.getValue().toString());
            }

        }else if(contentType.equals("application/x-www-form-urlencoded")){
            //方式一：使用 QueryStringDecoder
            String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
            QueryStringDecoder queryDecoder = new QueryStringDecoder(jsonStr, false);
            Map<String, List<String>> uriAttributes = queryDecoder.parameters();
            for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
                for (String attrVal : attr.getValue()) {
                    logger.info(attr.getKey() + "=" + attrVal);
                }
            }

        }else if(contentType.equals("multipart/form-data")){
            //TODO 用于文件上传
        }else{
            //do nothing...
        }
    }

    private String getContentType(){
        String typeStr = headers.get("Content-Type").toString();
        String[] list = typeStr.split(";");
        return list[0];
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
