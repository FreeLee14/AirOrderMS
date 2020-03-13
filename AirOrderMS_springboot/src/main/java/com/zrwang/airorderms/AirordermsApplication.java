package com.zrwang.airorderms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//添加mybatisplus进行mapper扫描的配置
@MapperScan("com.zrwang.airorderms.mapper")
@EnableCaching//开启缓存的注解
public class AirordermsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirordermsApplication.class, args);
    }

}
