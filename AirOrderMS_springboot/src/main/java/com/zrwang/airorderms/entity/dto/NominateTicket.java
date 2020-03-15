package com.zrwang.airorderms.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户进行传输推荐信息的dto
 */
@Data
public class NominateTicket implements Serializable {

    private Integer id;
    private String departure;
    private String destination;
    private Integer money;
}
