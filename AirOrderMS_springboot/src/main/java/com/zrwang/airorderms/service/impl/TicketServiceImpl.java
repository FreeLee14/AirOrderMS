package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrwang.airorderms.entity.Ticket;
import com.zrwang.airorderms.entity.dto.NominateTicket;
import com.zrwang.airorderms.entity.dto.TicketConditions;
import com.zrwang.airorderms.mapper.TicketMapper;
import com.zrwang.airorderms.service.TicketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger;


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

    /**
     * 根据详细条件进行精准查询检索
     * @param conditions
     * @return
     */
    @Override
    @Cacheable(cacheNames = "tickets" ,key = "#conditions.destination")
    public List<Ticket> findByConditions(TicketConditions conditions) {

        logger = LoggerFactory.getLogger(TicketServiceImpl.class);
        // 初始化包装器
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();

        // 机票分为单程和往返两种类型，所以分开进行检索
        // 针对复合索引进行sql的优化
        queryWrapper.select("id","departure","destination","TIME(start_time)AS start_time","TIME(end_time) AS end_time","flight","consume","money");
        if (conditions.getIsreturn() == 0){
            queryWrapper.eq("parentid",0);
            queryWrapper.eq("departure",conditions.getDeparture());
            queryWrapper.eq("destination",conditions.getDestination());
            queryWrapper.eq("DATE(start_time)",conditions.getStartTime());
            queryWrapper.eq("isreturn",conditions.getIsreturn());
        }

        if (conditions.getIsreturn() == 1){
            queryWrapper.eq("isreturn",conditions.getIsreturn());
        }

        List<Ticket> tickets = ticketMapper.selectList(queryWrapper);

        for (Ticket ticket: tickets){

            ticket.setStartTime(ticket.getStartTime().substring(0,5));
            ticket.setConsume(ticket.getConsume().substring(0,5));
            ticket.setEndTime(ticket.getEndTime().substring(0,5));

        }

        return tickets;
    }

}
