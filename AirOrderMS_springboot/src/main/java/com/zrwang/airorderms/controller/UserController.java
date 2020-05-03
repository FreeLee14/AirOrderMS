package com.zrwang.airorderms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrwang.airorderms.entity.RegistUser;
import com.zrwang.airorderms.entity.User;
import com.zrwang.airorderms.entity.vo.LoginUser;
import com.zrwang.airorderms.entity.dto.PrefactUser;
import com.zrwang.airorderms.service.OrderinfoService;
import com.zrwang.airorderms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private OrderinfoService orderinfoService;



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
    public LoginUser loginUser (String name, String password, String token, String isLogin)
    {

        LoginUser loginUser = userService.loginUser(name, password, token, isLogin);

        return loginUser;
    }

    // 注销方法 主要是将redis中的缓存令牌进行清除
    @DeleteMapping("/user")
    public boolean logoutUser(String name){

        boolean delete = userService.logoutUser(name);

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

        User user = userService.findByName(name);

        return user;

    }
}

