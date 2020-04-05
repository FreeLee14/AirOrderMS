package com.zrwang.airorderms.controller;


import com.zrwang.airorderms.entity.Orderinfo;
import com.zrwang.airorderms.entity.dto.CreateOrderInfo;
import com.zrwang.airorderms.service.OrderinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class OrderinfoController {

    @Autowired
    private OrderinfoService orderinfoService;

    @PostMapping("/orderinfo")
    public boolean createOrder(Orderinfo orderinfo) {

        boolean flag = false;

        flag = orderinfoService.createOrder(orderinfo);

        return flag;
    }

}

