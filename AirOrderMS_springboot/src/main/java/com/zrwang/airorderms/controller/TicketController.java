package com.zrwang.airorderms.controller;


import com.zrwang.airorderms.entity.dto.NominateTicket;
import com.zrwang.airorderms.service.impl.TicketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/ticket")
    public List<NominateTicket> findRandom(String address) {

        logger.info("开始查询推荐机票");
        List<NominateTicket> rondom = ticketService.findRandom(address);
        logger.info("查询结束并已缓存");
        return rondom;
    }

}

