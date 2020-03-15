package com.zrwang.airorderms.entity;

import lombok.Data;

@Data
public class RegistUser {

    private String name;
    private String phone;
    private String email;
    private String password;
    private String ensurepassword;


}
