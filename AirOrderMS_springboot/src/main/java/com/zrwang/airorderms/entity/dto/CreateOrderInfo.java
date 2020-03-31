package com.zrwang.airorderms.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于创建订单时前端接收传递的数据对象
 */

@Data
public class CreateOrderInfo implements Serializable {

    private Integer userId;

    private Integer ticketId;

    private Integer money;

    private Integer number;

    private Integer status;

    private String invalidTime;

    private String passName;

    private String passPhone;

    private String passIdcard;
}
