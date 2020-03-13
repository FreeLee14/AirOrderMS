package com.zrwang.airorderms.service.impl;

import com.zrwang.airorderms.entity.User;
import com.zrwang.airorderms.mapper.UserMapper;
import com.zrwang.airorderms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
