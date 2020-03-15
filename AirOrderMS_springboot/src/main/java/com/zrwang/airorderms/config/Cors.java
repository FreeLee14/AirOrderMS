package com.zrwang.airorderms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//解决前端vue项目请求后端api时产生的跨域问题
@Configuration
@EnableWebMvc
public class Cors {

    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置匀速跨域请求的域名
                .allowedOrigins("*")
                // 这里是否允许证书，true不再默认开启
                .allowCredentials(true)
                // 设置允许方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(60000);

    }
}
