package com.zrwang.airorderms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrwang.airorderms.entity.RegistUser;
import com.zrwang.airorderms.entity.User;
import com.zrwang.airorderms.mapper.UserMapper;
import com.zrwang.airorderms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zrwang
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/airorderms")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

//    @GetMapping("/user")
//    public List<User> findAllUser(){
//
//        List<User> users = userService.list(null);
//
//        return users;
//    }


    // 注册方法
    @PostMapping("/user")
    public boolean registUser( RegistUser registUser){

        Logger logger = LoggerFactory.getLogger(UserController.class);


        User user = new User();

        //注册时有些数据是不会完全录入的所以在这里，但是数据库不允许为空，所以在这里将注册没有填写的数据先进性一次预处理。
        user.setName(registUser.getName());
        user.setGender("0");
        user.setPassword(registUser.getPassword());
        user.setPhone(registUser.getPhone());
        user.setEmail(registUser.getEmail());
        user.setIdCard("-1");
        user.setBirthday(Date.valueOf("1970-01-01"));
        user.setAddress("-1");

        boolean save = userService.save(user);

        if (save){

            logger.debug("注册成功");
            logger.info("注册成功");

        }else {

            logger.debug("注册失败");
            logger.info("注册失败");

        }
        // 返回Boolean类型的结果，为true注册成功，false注册失败
        return save;
    }

    // 登录方法
    @GetMapping("/user")
    public String loginUser ( String name, String password, String token, String isLogin)
    {

        Logger logger = LoggerFactory.getLogger(UserController.class);
        // 判断如果令牌为null或者是空就随机生成一个令牌
        if(token==null || token.isEmpty()){

            QueryWrapper<User> wrapper = new QueryWrapper();
            wrapper.eq("name",name);
            wrapper.select("password");
            User user = userMapper.selectOne(wrapper);
            // 获取查询到的密码
            String findPass = user.getPassword();

            if (password.contentEquals(findPass)){

                logger.info("密码校验成功");
                logger.info("开始生成令牌");

                token = UUID.randomUUID().toString();
                stringRedisTemplate.opsForValue().set(name,token);
                // 指定过期时间为20s进行测试
                stringRedisTemplate.expire(name,3600, TimeUnit.SECONDS);

                String keyName = stringRedisTemplate.opsForValue().get(name);
                System.out.println(keyName);

            }

        }

        return token;
    }
    // 注销方法 主要是将redis中的缓存令牌进行清除
    @DeleteMapping("/user")
    public boolean logoutUser(String name){

        Logger logger = LoggerFactory.getLogger(UserController.class);
        // 删除redis中存储的token
        Boolean delete = stringRedisTemplate.delete(name);

        if (delete == true){

            logger.info("注销成功");

        }else{

            logger.info("注销失败");

        }

        return delete;

    }
}

