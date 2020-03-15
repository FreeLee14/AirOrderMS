package com.zrwang.airorderms.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TicketView {

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
     * 起飞日期
     */
    private String startDate;

    /**
     * 起飞时间
     */
    private String startTime;

    /**
     * 行程时间
     */
    private String consume;

    /**
     * 结束时间
     */
    private String endTime;

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
}
