package com.zrwang.airorderms.entity.dto;

import lombok.Data;

@Data
public class LoginUser {

    private String name;
    private String password;
    private String token;
    private Integer isLogin;
}
