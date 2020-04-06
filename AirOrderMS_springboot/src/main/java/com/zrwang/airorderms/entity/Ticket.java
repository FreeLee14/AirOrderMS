package com.zrwang.airorderms.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Timestamp;
import java.util.Date;
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
public class Ticket implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 代表着不同的座位依赖于同一个飞机
     */
    private Integer parentid;

    /**
     * 航班班次
     */
    private String flight;

    /**
     * 座位
     */
    private String seat;

    /**
     * 出发地
     */
    private String departure;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 起飞时间
     */
    private String startTime;

    /**
     * 行程时间
     */
    private String consume;

    /**
     * 机票数量
     */
    private Integer counts;

    /**
     * 机票金额
     */
    private Integer money;

    /**
     * 是否往返
     */
    private Integer isreturn;

    /**
     * 结束日期
     */
    private String endTime;

}
