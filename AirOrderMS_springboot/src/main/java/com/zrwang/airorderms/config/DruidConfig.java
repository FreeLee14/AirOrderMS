package com.zrwang.airorderms.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//配置druid连接池信息
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {

        return new DruidDataSource();

    }

    /*配置druid监控器的servlet以及对应的filter*/
    @Bean
    public ServletRegistrationBean statViewServlet(){

        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //登录druid监控页面查看信息的账号密码
        registrationBean.addInitParameter("loginUsername","admin");
        registrationBean.addInitParameter("loginPassword","123456");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){

        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        //添加过滤规则
        registrationBean.addUrlPatterns("/*");
        //定义不拦截的文件和路径
        registrationBean.addInitParameter("exclusions","*.js,*.css,*.img,/druid/*");

        return registrationBean;
    }
}
