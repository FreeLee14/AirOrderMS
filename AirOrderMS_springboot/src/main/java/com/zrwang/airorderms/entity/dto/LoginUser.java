package com.zrwang.airorderms.entity.dto;

import lombok.Data;

/**
 * 用于登录时前端传递后端的数据实体
 */
@Data
public class LoginUser {

    private String name;

    private String password;

    private String token;

    private Integer isLogin;

}
