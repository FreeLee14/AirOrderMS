package com.zrwang.airorderms.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 登录过滤器
public class LoginFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.info("开始进行登录拦截");
        logger.debug("开始进行登录拦截");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        rep.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        rep.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        rep.setHeader("Access-Control-Max-Age", "60000");
        rep.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");
        // 设置返回的字符集
        response.setCharacterEncoding("UTF-8");
        // 设置返回的conten-type
        response.setContentType("application/json; charset=utf-8");

        String isLogin = req.getParameter("isLogin");
        String token = req.getParameter("token");

//        if(isLogin.equals("1") && token!=null){
            //如果监测到当前登录状态为已登录，并且token不为空，那么就对该请求进行放行
            filterChain.doFilter(request,response);
//        }

    }
    @Override
    public void destroy() {

    }
}
