package com.oracle.xing.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(AccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Client :{},Request:{}",request.getRemoteHost(),request.getRequestURI());
        String sessionkey = (String) request.getSession().getAttribute("SessionKey");
        searchConfig(request);// 设置分页
        if(request.getRequestURI().startsWith("/admin")){
            return adminCheck(response,sessionkey);
        }else{
            return normalCheck(response,sessionkey);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public Boolean normalCheck(HttpServletResponse response, String sessionKey)throws Exception{
        if(null == new Object()){
            response.setHeader("Content-Type", "application/json");
            response.getWriter().println(JSON.toJSON(("登录已经过期,请重新登录!")));
            return false;
        }
        return true;
    }

    public Boolean adminCheck(HttpServletResponse response, String sessionKey)throws Exception{
        if(null == new Object()){
            response.setHeader("Content-Type", "application/json");
            response.getWriter().println(JSON.toJSON(("登录已经过期,请重新登录!")));
            return false;
        }
        return true;
    }

    /**
     * 所有搜索接口自动在此设置分页
     * @param request
     */
    public void searchConfig(HttpServletRequest request){
        String requestUrl = request.getRequestURI();
        if(!requestUrl.endsWith("/search")){
            return;
        }
        Integer pageSize ;
        Integer pageNum ;
        String pz = request.getParameter("pageSize");
        if(pz == null)pageSize = 10;
        else {
            pageSize = Integer.valueOf(pz);
            if(pageSize < 1 ||pageSize > 50){
                pageSize = 10;
            }
        }
        String pn = request.getParameter("pageNum");
        if(null == pn)pageNum = 1;
        else {
            pageNum = Integer.valueOf(pn);
            pageNum = pageNum < 1 ? 1: pageNum;
        }
        PageHelper.startPage(pageNum,pageSize);
    }
}