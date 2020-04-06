package com.zrwang.airorderms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrwang.airorderms.entity.Ticket;
import com.zrwang.airorderms.entity.vo.NominateTicket;
import com.zrwang.airorderms.entity.dto.TicketConditions;
import com.zrwang.airorderms.entity.vo.TicketView;
import com.zrwang.airorderms.service.impl.TicketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/airorderms")
@CrossOrigin
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/ticket")
    public List<NominateTicket> findRandom(String address) {

        logger.info("开始查询推荐机票");
        List<NominateTicket> random = ticketService.findRandom(address);
        logger.info("查询结束并已缓存");
        return random;
    }
    // 后期需要针对这个方法进行改造，性能太差
    @GetMapping("/ticket/{id}")
    @Cacheable(cacheNames = "TicketById" , key = "#id",cacheManager = "redisCacheManager")
    public List<TicketView> findById(@PathVariable(value = "id") Integer id) throws ParseException {

        logger.info("开始根据id查询票据信息");
        Ticket ticket = ticketService.getById(id);

        String startDateStr = ticket.getStartTime();
        //获取开始日期（同时在这个业务中代表着结束日期）
        String startDate = startDateStr.substring(0, 10);
        // 获取开始时间
        String startTime = startDateStr.substring(11,16);
        // 获取结束时间
        String endTime = ticket.getEndTime().substring(11,16);
        TicketView ticketView = new TicketView();

        ticketView.setId(ticket.getId());
        ticketView.setConsume(ticket.getConsume().substring(0,5));
        ticketView.setCounts(ticket.getCounts());
        ticketView.setDeparture(ticket.getDeparture());
        ticketView.setDestination(ticket.getDestination());
        ticketView.setFlight(ticket.getFlight());
        ticketView.setIsreturn(ticket.getIsreturn());
        ticketView.setMoney(ticket.getMoney());
        ticketView.setStartTime(startTime);
        ticketView.setStartDate(startDate);
        ticketView.setEndTime(endTime);

        List<TicketView> ticketViews = new ArrayList<>();

        ticketViews.add(ticketView);

        return ticketViews;
    }

    @PostMapping("/ticket")
    public  List<Ticket> findByConditions(TicketConditions conditions){

        List<Ticket> ticketViews = ticketService.findByConditions(conditions);

        return ticketViews;

    }
    @Cacheable( cacheManager = "redisCacheManager",cacheNames = "pageInfo", key = "#currentPage",unless = "#result == null ")
    @GetMapping("/ticket/{currentPage}/{pageCounts}")
    public Map<String,Object> showAllByPage(@PathVariable(value = "currentPage") Integer currentPage,
                                      @PathVariable(value = "pageCounts") Integer pageCounts) {

        Map<String,Object> allTickets = ticketService.showAllByPage(currentPage,pageCounts);

        return allTickets;

    }

}

