package com.zrwang.airorderms.controller;


import com.zrwang.airorderms.entity.Orderinfo;
import com.zrwang.airorderms.entity.dto.CreateOrderInfo;
import com.zrwang.airorderms.entity.dto.EnsureOrder;
import com.zrwang.airorderms.service.OrderinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@CrossOrigin
public class OrderinfoController {

    @Autowired
    private OrderinfoService orderinfoService;

    /**
     * 创建订单
     * @param orderinfo
     * @return
     */
    @PostMapping("/orderinfo")
    public boolean createOrder(Orderinfo orderinfo) {

        boolean flag = false;

        flag = orderinfoService.createOrder(orderinfo);

        return flag;
    }

    /**
     * 确认订单
     * @param ensureOrder
     * @return
     * RequestParam注解映射表单中的某一个字段 给参数
     */
    @PatchMapping("/orderinfo")
    public boolean ensureOrder(EnsureOrder ensureOrder) {

        boolean flag = false;

        String  orderId = ensureOrder.getOrderId();
        Integer status = ensureOrder.getStatus();
        Integer ticketId = ensureOrder.getTicketId();

        flag = orderinfoService.ensureOrder(orderId,status,ticketId);

        return flag;

    }

    /**
     * 查看成功订单 (当查询完所有订单后，以userid为关键字进行缓存的存储) 如果查询出来的是空对象则不进行缓存（但是埋下了缓存穿透的隐患）
     * @param userId
     * @return
     */
    @Cacheable(cacheManager = "redisCacheManager" ,cacheNames = "orders",key = "#userId",unless="#result == null")
    @GetMapping("/orderinfo")
    public List<Orderinfo> findAllOrder(Integer userId) {

        List<Orderinfo> orderinfoList;

        orderinfoList = orderinfoService.findAllOrder(userId);
        if (orderinfoList.size() == 0)
            orderinfoList = null;
        return orderinfoList;

    }

    /**
     * 退订业务 （当我们进行退订业务时，根据我们存储缓存所有到的key：userid去删除所有的订单缓存）
     * @param orderId
     * @return
     */
    @CacheEvict(cacheManager = "redisCacheManager", allEntries = false,beforeInvocation = true, cacheNames = "orders",key = "#userId")
    @DeleteMapping("orderinfo")
    public List<Orderinfo> deleteOrder(String orderId, Integer userId){

        boolean flag = false;
        List<Orderinfo> allOrder = new ArrayList<>();

        flag = orderinfoService.deleteOrder(orderId,userId);
        // 如果删除成功的话，开始从新查询数据库并将数据更新至页面同时更新至缓存中
        if (flag) {

            allOrder = findAllOrder(userId);

        }

        return allOrder;
    }

}

