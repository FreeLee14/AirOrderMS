package com.zrwang.airorderms.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于完善信息时前后端传递数据的实体
 */
@Data
public class PrefactUser implements Serializable {

    /**
     *  用户真实姓名
     */
    private String name;
    /**
     *  性别
     */
    private String gender;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     *  出生年月
     */
    private String birthday;
    /**
     *  家庭住址
     */
    private String address;
    /**
     * 用户头像base64数据流
     */
    private String imgInfo;

}
