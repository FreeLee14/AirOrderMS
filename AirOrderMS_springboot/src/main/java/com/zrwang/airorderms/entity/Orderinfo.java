package com.zrwang.airorderms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orderinfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 定义外键
    @TableField("user_id")
    private Integer userId;
    // 定义外键
    @TableField("ticket_id")
    private Integer ticketId;

    private Integer number;

    private String purchasetime;

    private String invalidtime;

    private Integer money;

    private Integer status;

    private String passName;

    private String passPhone;

    private String passIdcard;

    private String orderId;

    private String seat;


}
