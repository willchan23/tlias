package org.example.tlias.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {//doFilter必须重写，其它两个可以不用
    @Override//初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Demo初始化方法执行了");
    }

    @Override//拦截到请求之后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo拦截到了请求，放行前的逻辑");
        filterChain.doFilter(servletRequest,servletResponse);//放行
        System.out.println("Demo拦截到了请求，放行后的逻辑");
    }

    @Override//销毁方法，只调用一次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Demo销毁方法执行了");
    }
}
