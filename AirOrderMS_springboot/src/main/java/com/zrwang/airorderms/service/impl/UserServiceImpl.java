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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public LoginUser loginUser(String name, String password, String token, String isLogin) {


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

    /**
     * 根据用户id获取地址的该用户地址的方法实现
     * @param id
     * @return
     */
    @Override
    public String getAddress(Integer id) {
        logger.info("开始查询当前用户地址");
        // id为0代表没有用户登录，此时直接返回null
        if(id == 0) {
            logger.info("当前没有用户登录");
            return null;
        }
        logger.info("开始查询");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("address");
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        logger.info("查询结束并返回");
        return user.getAddress();
    }

    /**
     * 根据名字查找用户的信息
     * @param name
     * @return
     */
    @Override
    @Cacheable(cacheManager = "redisCacheManager",cacheNames = "findByName",key = "#name")
    public User findByName(String name) {

        User user = new User();
        if(name == null && name.length() == 0) return user;
        logger.info("开始查找用户的信息");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);

        user = userMapper.selectOne(queryWrapper);
        logger.info("查询结束");
        return user;
    }

    /**
     * 注销方法（注销过程中需要将用户的token缓存存在则进行删除,以及用户的个人信息）
     * @param name
     * @return
     */
    @Override
    @CacheEvict(cacheManager = "redisCacheManager", cacheNames = "findByName", key = "#name")
    public boolean logoutUser(String name) {

        boolean delete = false;

        String token = stringRedisTemplate.opsForValue().get(name);

        if(token != null && token.length() > 0){
            delete = stringRedisTemplate.delete(name);
            logger.info("已删除用户token缓存");
        }else {
            // 如果没有用户缓存说明已经过期，则直接进行注销即可
            logger.info("用户token缓存已经过期，直接进行注销");

            delete = true;
        }


        return delete;
    }
}
