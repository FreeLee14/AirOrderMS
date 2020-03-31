package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrwang.airorderms.entity.User;
import com.zrwang.airorderms.entity.vo.LoginUser;
import com.zrwang.airorderms.mapper.UserMapper;
import com.zrwang.airorderms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zrwang
 * @since 2020-03-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginUser loginUser(String name, String password, String token, String isLogin) {

        Logger logger = LoggerFactory.getLogger(UserService.class);

        LoginUser loginUser = null;

        // 判断如果令牌为null或者是空就随机生成一个令牌, 后期添加商为防止重复登录的流程
        if(token==null || token.isEmpty()){

            QueryWrapper<User> wrapper = new QueryWrapper();
            wrapper.eq("name",name);
            wrapper.select("id","name","password","email","phone");
            User user = userMapper.selectOne(wrapper);
            // 获取查询到的密码
            String findPass = user.getPassword();
            Integer id = user.getId();
            String phone = user.getPhone();
            String email = user.getEmail();

            if (password.contentEquals(findPass)){

                logger.info("密码校验成功");
                logger.info("开始生成令牌");

                token = UUID.randomUUID().toString();
                // 将获取到的token以及姓名作为键值对作为缓存存储在redis中，进行权限校验时使用
                stringRedisTemplate.opsForValue().set(name,token);
                // 指定过期时间为1小时
                stringRedisTemplate.expire(name,3600, TimeUnit.SECONDS);

                String keyName = stringRedisTemplate.opsForValue().get(name);
                System.out.println(keyName);
                // 封装传递给前端的数据对象
                loginUser = new LoginUser(id,name,token,phone,email);
            }

        }
        return loginUser;

    }
}
