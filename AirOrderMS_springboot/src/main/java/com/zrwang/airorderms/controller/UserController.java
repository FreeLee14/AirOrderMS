package com.zrwang.airorderms.controller;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrwang.airorderms.entity.RegistUser;
import com.zrwang.airorderms.entity.User;
import com.zrwang.airorderms.entity.dto.PrefactUser;
import com.zrwang.airorderms.mapper.UserMapper;
import com.zrwang.airorderms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
        user.setBirthday("19700101");
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

    // 在设置页面进行信息完善的api,进行图片信息流的保存
    @PostMapping("/user/{name}")
    public boolean prefactInfo(@PathVariable(value = "name" ) String name , PrefactUser prefactUser){

        Logger logger = LoggerFactory.getLogger(UserController.class);

        // 组装update语句实体
        UpdateWrapper<User> userWrapper = new UpdateWrapper<>();

        userWrapper.set("gender",prefactUser.getGender());
        userWrapper.set("id_card",prefactUser.getIdCard());
        userWrapper.set("birthday",prefactUser.getBirthday());
        userWrapper.set("address",prefactUser.getAddress());
        userWrapper.set("img_info",prefactUser.getImgInfo());

        userWrapper.eq("name",name);

        // 进行更新
        boolean b = userService.update(userWrapper);

        if( b ) {
            logger.info("完善成功");
        }else {
            logger.info("完善失败");
        }
        return b;

    }
    // 根据名称来查询用户的所有信息,数据库中用户表姓名字段已添加索引，所以查询性能并未降低
    @GetMapping("/user/{name}")
    public User findByName (@PathVariable(value = "name") String name){

        QueryWrapper<User> userWrapper = new QueryWrapper<>();

        userWrapper.eq("name",name);
        User user = userService.getOne(userWrapper);

        return user;

    }
}

