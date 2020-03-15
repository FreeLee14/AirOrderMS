package com.zrwang.airorderms;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zrwang");
        gc.setOpen(false);
        gc.setIdType(IdType.AUTO); //设置主键生成策略为数据库自增
        gc.setFileOverride(false);// 是否覆盖文件
        gc.setServiceName("%sService"); // 所有生成的service接口首字母I去除
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.48.128:3306/airorderms?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1484840359");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zrwang");
        pc.setModuleName("airorderms"); //设置模块名
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude(""); //映射的需要生成的表
        strategy.setNaming(NamingStrategy.underline_to_camel);// 开启驼峰命名法
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//自动添加lombok的注解
        strategy.setRestControllerStyle(true); //生成restful的接口
        strategy.setControllerMappingHyphenStyle(true);  //url驼峰名 转化为_
        mpg.setStrategy(strategy);
        mpg.execute();


    }
}
