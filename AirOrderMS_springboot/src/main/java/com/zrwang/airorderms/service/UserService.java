package com.zrwang.airorderms.service;

import com.zrwang.airorderms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrwang.airorderms.entity.vo.LoginUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrwang
 * @since 2020-03-09
 */
public interface UserService extends IService<User> {

    // 登录方法接口
     LoginUser loginUser(String name, String password, String token, String isLogin);
}
