package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrwang.airorderms.entity.Ticket;
import com.zrwang.airorderms.entity.Tourisminfo;
import com.zrwang.airorderms.mapper.TourisminfoMapper;
import com.zrwang.airorderms.service.OrderinfoService;
import com.zrwang.airorderms.service.TourisminfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrwang.airorderms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zrwang
 * @since 2020-04-28
 */
@Service
public class TourisminfoServiceImpl extends ServiceImpl<TourisminfoMapper, Tourisminfo> implements TourisminfoService {

    @Autowired
    private TourisminfoMapper tourisminfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderinfoService orderinfoService;
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger;
    // 用户地址
    private static String address;
    // 用户的行程信息
    private static List<Ticket> myRoute;
    /**
     * 根据景点所在地进行分页查询相关地点的所有景点（如果没有提供景点所在地就随机进行查询）
     * @param Id  用户id
     * @param currentPage 当前页数
     * @param pageCounts  一页数据个数
     * @return
     */
    @Override
    public Map<String,Object> showAllTourism(Integer Id, Integer currentPage, Integer pageCounts) {

        logger = LoggerFactory.getLogger(TourisminfoServiceImpl.class);
        Map<String,Object> res = new HashMap<>();
        QueryWrapper<Tourisminfo> queryWrapper = new QueryWrapper<>();
        // 初始化存储景点的容器
        Set<String> set = new HashSet<>();
        // 初始化mybatis-plus范围查询in所需要的动态数组
        Object[] locate = null;
        // 同时开启两个线程去获取数据并阻塞主线程，直到所有的数据获取完毕
        // 获取用户的住址
        Thread t1 = new Thread(() -> {
            address = userService.getAddress(Id);
            if(address != null) address = address.substring(0,2);
        },"t1");
        // 获取用户的行程信息
        Thread t2 = new Thread(() -> myRoute = orderinfoService.findMyRoute(Id),"t2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(address != null && myRoute != null){
            // 如果用户地址不为空且用户已经下单，此时针对用户的订单信息中的所有目的地进行范围查询
            // 先将不重复的所有地点存储起来
            for (Ticket item: myRoute) set.add(item.getDestination());
            locate = set.toArray();
            queryWrapper.in("tourism_locate",locate);

        }else if(address != null && myRoute == null){
            // 如果用户地址不为空且订单为空，则证明此时用户还没有下单
            queryWrapper.eq("tourism_locate",address);
        }

        logger.info("开始进行分页查询");

        Page<Tourisminfo> page = new Page<>(currentPage,pageCounts);

        Page<Tourisminfo> tourisminfoPage = tourisminfoMapper.selectPage(page, queryWrapper);

        res.put("records",tourisminfoPage.getRecords());
        res.put("total",tourisminfoPage.getTotal());

        logger.info("查询分页结束");

        return res;
    }
}
