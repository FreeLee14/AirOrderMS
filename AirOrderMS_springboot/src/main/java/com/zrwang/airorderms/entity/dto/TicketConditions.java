package com.zrwang.airorderms.entity.dto;

import lombok.Data;

@Data
public class TicketConditions {

    /**
     * 单程还是往返
     */
    private Integer isreturn;
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
    private String startTime;
    /**
     * 结束日期
     */
    private String endTime;

}
