package com.zrwang.airorderms.service;

import com.zrwang.airorderms.entity.Orderinfo;
import com.zrwang.airorderms.entity.Ticket;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrwang.airorderms.entity.dto.NominateTicket;
import com.zrwang.airorderms.entity.dto.TicketConditions;
import com.zrwang.airorderms.entity.vo.TicketView;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */
public interface TicketService extends IService<Ticket> {

    /**
     * 推荐机票的接口
     * @param departure
     * @return
     */
    List<NominateTicket> findRandom(String departure);

    /**
     * 根据复合条件进行精准查询机票信息
     * @param conditions
     * @return
     */
    List<Ticket> findByConditions(TicketConditions conditions);
}
