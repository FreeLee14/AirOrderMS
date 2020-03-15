package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrwang.airorderms.entity.Ticket;
import com.zrwang.airorderms.entity.dto.NominateTicket;
import com.zrwang.airorderms.mapper.TicketMapper;
import com.zrwang.airorderms.service.TicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */

@Service
@CacheConfig(cacheManager = "redisCacheManager") //抽取cache的公共配置
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;



    @Override
    @Cacheable(cacheNames = "NominateTicket")
    public List<NominateTicket> findRandom(String departure) {


        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.select("id","departure","destination","money");
        wrapper.eq("departure",departure);
        wrapper.eq("parentid",0);

        List<Ticket> tickets = ticketMapper.selectList(wrapper);

        List<NominateTicket> nominateTickets = new ArrayList<>();

        for (Ticket ticket:tickets){

            NominateTicket nominateTicket = new NominateTicket();

            nominateTicket.setId(ticket.getId());
            nominateTicket.setDeparture(ticket.getDeparture());
            nominateTicket.setDestination(ticket.getDestination());
            nominateTicket.setMoney(ticket.getMoney());

            nominateTickets.add(nominateTicket);
        }

        return nominateTickets;
    }
}
