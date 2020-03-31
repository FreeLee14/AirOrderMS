package com.zrwang.airorderms.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于登录时前端传递后端的数据实体
 */
@Data
public class LoginUser implements Serializable {

    private Integer id;

    private String name;

    private String token;

    private String phone;

    private String email;

    public LoginUser(Integer id, String name, String token, String phone, String email) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.phone = phone;
        this.email = email;
    }
}
