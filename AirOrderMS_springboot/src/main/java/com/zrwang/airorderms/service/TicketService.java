package com.zrwang.airorderms.service;

import com.zrwang.airorderms.entity.Ticket;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrwang.airorderms.entity.dto.NominateTicket;

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

    public List<NominateTicket> findRandom(String departure);
}
