package org.example.tlias.filter;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.StringUtil;
import org.example.tlias.pojo.Result;
import org.example.tlias.utils.JwtUtils;
import org.springframework.util.StringUtils;

import java.awt.datatransfer.FlavorListener;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求url
        HttpServletRequest req = (HttpServletRequest) servletRequest;//servletRequest没有geturl方法，所以要转成子类httpservletrequest
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}",url);
        //2.判断url中是否包含login
        if(url.contains("login")){
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3.获取请求头中的令牌
        String jwt = req.getHeader("token");
        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录状态）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error= Result.error("NOT_LOGIN");//由于不在controller中，没有restcontroller的自动转json
            //手动转化 对象->json 阿里巴巴fastjson
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录状态）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析失败，返回错误结果");
            Result error= Result.error("NOT_LOGIN");//由于不在controller中，没有restcontroller的自动转json
            //手动转化 对象->json 阿里巴巴fastjson
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
