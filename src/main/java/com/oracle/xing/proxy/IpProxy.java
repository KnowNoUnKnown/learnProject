package com.oracle.xing.proxy;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liuyong
 * 2018-11-05  14-32
 *
 * 根据ip查询归属地
 *
 */

public class IpProxy {

    public static final String URL = "https://ip.cn/index.php";

    public static WebClient webClient ;

    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        webClient.addRequestHeader("user-agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
    }


    public static void main(String []args)throws Exception{

        getdynamicIpPooling(null);

        InetAddress addr = InetAddress.getLocalHost();

        String ip=addr.getHostAddress().toString(); //获取本机ip

        Document document = Jsoup.connect(URL).proxy(
                new Proxy(Proxy.Type.HTTP,new InetSocketAddress("118.190.95.43",9001)))
                        .get();

        List<Element> element = document.getElementById("result").getElementsByTag("code");

        Map<String ,String> result = new HashMap<>();

        result.put("ipAddress",element.get(0).html());

        result.put("realAddress",element.get(1).html());

        System.out.println(JSON.toJSONString(result));


    }


    /**
     * 获取代理ip池
     * @return
     */
    public static Map<String ,Integer > getdynamicIpPooling(ConcurrentHashMap<String ,Integer > ipPoolMap )throws Exception{
        int maxPage = 20;
        for(int i = 0 ; i < maxPage ; i++){
            String content = webClient.getPage("http://www.xicidaili.com/nn/"+i).getWebResponse().getContentAsString();
            Document document = Jsoup.parse(content);
            List<Element> elementList = document.getElementsByTag("tbody").get(0).getElementsByTag("tr");
            elementList.remove(0);
            for(Element element : elementList){
                System.out.println(element);
                List<Element> elements = element.children();
                ipPoolMap.put(elements.get(1).html(),Integer.valueOf(elements.get(2).html()));
            }
            Thread.sleep(5000);
        }
        return ipPoolMap;
    }

    public static Boolean checkIpAviable(String ipAddress,Integer port){
        try {
            Document document = Jsoup.connect(URL).proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("118.190.95.43",9001)))
             .get();
            System.out.println(document.html());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
