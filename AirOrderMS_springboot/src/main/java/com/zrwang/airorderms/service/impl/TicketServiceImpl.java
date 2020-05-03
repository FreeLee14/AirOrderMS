package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrwang.airorderms.entity.Ticket;
import com.zrwang.airorderms.entity.vo.NominateTicket;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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

    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);


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

    /**
     * 实现分页查询所有机票信息
     * @param currentPage
     * @param pageCounts
     * @return
     */
    @Override
    public Map<String,Object> showAllByPage(Integer currentPage, Integer pageCounts) {

        logger = LoggerFactory.getLogger(TicketServiceImpl.class);
        Map<String,Object> result = new HashMap<>();

        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentid",0).last("ORDER BY id");


        Page<Ticket> page = new Page<>(currentPage,pageCounts);
        logger.info("开始分页查询");

        IPage<Ticket> ticketIPage = ticketMapper.selectPage(page,queryWrapper);

        logger.info("查询结束，开始组装结果集");
        // 需要把分页查询出来的机票信息和总记录数存入到map中，方便结合redis缓存
        result.put("records",ticketIPage.getRecords());
        result.put("total",ticketIPage.getTotal());

        return result;

    }

    /**
     * 更新机票状态
     * @param id
     */
    @Override
    public void updateTicket(AtomicReference<Integer> id,Integer status) {

        if (status == 1)
            logger.info("开始更新机票状态为已购买");
        else if (status == 0)
            logger.info("由于退票，开始更新机票状态为可被购买");

        UpdateWrapper<Ticket> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("ticket_status",status);
        // 转换为整型，id经过原子类传递过来的是一个double类型
        Integer ticketId = id.get();
        updateWrapper.eq("id",ticketId);
        int update = ticketMapper.update(null, updateWrapper);

        if (update > 0)
            logger.info("更新状态成功");

    }

}
