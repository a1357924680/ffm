package com.family.financial.management.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhangyiping on 2017/10/17.
 */
@ComponentScan
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "sessionUser";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/user/login");
        addInterceptor.excludePathPatterns("/user/register");
        addInterceptor.excludePathPatterns("/view/login");
        addInterceptor.excludePathPatterns("/view/register");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null) {
                return true;
            }
            // 跳转登录
            JSONObject json = new JSONObject();
            json.put("code","520");
            json.put("msg","未登录");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json.toString());
            return false;
        }
    }
}
