package com.zrwang.airorderms.entity.dto;

import lombok.Data;

import java.io.Serializable;

//@RequestParam("orderId") String orderId,@RequestParam("status") Integer status,@RequestParam("ticketId") Integer ticketId
@Data
public class EnsureOrder implements Serializable {


    private String orderId;

    private Integer status;

    private Integer ticketId;

}
