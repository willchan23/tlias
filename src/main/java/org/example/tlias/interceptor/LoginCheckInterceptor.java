package org.example.tlias.interceptor;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.tlias.pojo.Result;
import org.example.tlias.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//资源方法运行前运行，返回true则放行，返回false则不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        System.out.println("preHandle...");
        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}", url);
        //2.判断url中是否包含login
        if (url.contains("login")) {
            log.info("登录操作，放行");
            return true;
        }
        //3.获取请求头中的令牌
        String jwt = req.getHeader("token");
        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录状态）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");//由于不在controller中，没有restcontroller的自动转json
            //手动转化 对象->json 阿里巴巴fastjson
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录状态）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析失败，返回错误结果");
            Result error = Result.error("NOT_LOGIN");//由于不在controller中，没有restcontroller的自动转json
            //手动转化 对象->json 阿里巴巴fastjson
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        //6.放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override//资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("afterCompletion...");
    }
}
