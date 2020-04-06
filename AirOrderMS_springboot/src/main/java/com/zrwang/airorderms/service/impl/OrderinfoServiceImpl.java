package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrwang.airorderms.entity.Orderinfo;
import com.zrwang.airorderms.entity.Ticket;
import com.zrwang.airorderms.mapper.OrderinfoMapper;
import com.zrwang.airorderms.mapper.TicketMapper;
import com.zrwang.airorderms.service.OrderinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
public class OrderinfoServiceImpl extends ServiceImpl<OrderinfoMapper, Orderinfo> implements OrderinfoService {

    @Autowired
    private OrderinfoMapper orderinfoMapper;
    @Autowired
    private TicketMapper ticketMapper;

    private Logger logger;

    @Override
    /**
     * 创建订单方法
     * date: 2020.03.30
     * type: boolean
     */
    public boolean createOrder(Orderinfo orderinfo) {

        logger = LoggerFactory.getLogger(OrderinfoServiceImpl.class);

        boolean flag = false;
        String invalidTime = "";
        // 获取系统当前时间
        Long purchasetime = System.currentTimeMillis();
        Date purDate = new Date(purchasetime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        logger.info("开始查询对应的机票的起飞日期");
        // 根据机票Id查询出要起飞的时间，当作是订单的失效时间
        int ticketId = orderinfo.getTicketId();
        // 封装查询机票的查询类，我们只需要查询处起飞时间即可（目的是节省数据库开销）
        QueryWrapper<Ticket> ticketWrapper = new QueryWrapper();
        ticketWrapper.select("start_time");
        ticketWrapper.eq("id", ticketId);

        Ticket ticket = ticketMapper.selectOne(ticketWrapper);
        logger.info("查询结束");

        invalidTime = ticket.getStartTime();
        String purchaseTime = simpleDateFormat.format(purDate);
        //  封装我们最后要持久化的订单信息
        orderinfo.setInvalidtime(invalidTime);
        orderinfo.setPurchasetime(purchaseTime);

        logger.info("开始保存订单信息");
        int insert = orderinfoMapper.insert(orderinfo);

        if (insert > 0){
            flag = true;
            logger.info("保存成功");
        }else{
            logger.info("保存失败");
        }

        return flag;
    }
    // 确认订单方法（已经支付成功）
    @Override
    public boolean ensureOrder(String orderId,Integer status,Integer ticketId) {

        boolean flag = false;
        logger = LoggerFactory.getLogger(OrderinfoServiceImpl.class);

        logger.info("查询机票余票");
        String seat = updateOrder(ticketId, status);
        // 判断如果没有机票直接返回false
        if (seat == null)
            return flag;

        logger.info("开始更新订单");

        UpdateWrapper<Orderinfo> updateWrapper = new UpdateWrapper<>();

        updateWrapper.set("status",status);
        updateWrapper.set("seat",seat);
        updateWrapper.eq("order_id",orderId);

        int update = orderinfoMapper.update(null, updateWrapper);

        if (update > 0)
            flag = true;

        if (status == 2 && flag == true)
            logger.info("确认订单成功");
        if (status == 3 && flag == true)
            logger.info("下单成功");

        return flag;
    }

    /**
     * 确认订单时需要随机出票，需要查询出具体机票的座位信息,同时删除这张机票的信息
     * @param ticketId
     * @param staus
     * @return
     */
    @Override
    public String updateOrder(Integer ticketId, Integer staus) {

        logger = LoggerFactory.getLogger(OrderinfoServiceImpl.class);
        logger.info("开始查询具体的余票信息，并随机返回一张座位机票");
        QueryWrapper<Ticket> ticketQueryWrapper = new QueryWrapper<>();

        ticketQueryWrapper.select("seat");
        // 后面跟上last方法可补充我们想要的结尾sql，在这里分页查询只显示一条机票信息
        ticketQueryWrapper.eq("parentid",ticketId).last("limit 1");
        logger.info("进行随机出票");
        Ticket ticket = ticketMapper.selectOne(ticketQueryWrapper);
//        logger.info("删除这张机票");
//        ticketMapper.delete(ticketQueryWrapper);
        if (ticket == null)
            return null;
        else
            return ticket.getSeat();
    }

    /**
     * 根据用户id查询所有订单
     * @param userId
     * @return
     */
    @Override
    public List<Orderinfo> findAllOrder(Integer userId) {

        List<Orderinfo> orderinfoList;
        logger = LoggerFactory.getLogger(OrderinfoServiceImpl.class);
        QueryWrapper<Orderinfo> orderinfoQueryWrapper = new QueryWrapper<>();

        logger.info("开始查询所有已完成订单");
        orderinfoQueryWrapper.eq("user_id",userId);
        orderinfoQueryWrapper.eq("status",2);

        orderinfoList = orderinfoMapper.selectList(orderinfoQueryWrapper);
        logger.info("查询结束并已缓存");

        return orderinfoList;
    }

    /**
     * 将查出来要退订的机票进行更新状态为已退订，同时进行恢复这张机票的状态
     * @param userId
     * @param orderId
     * @return
     */
    @Override
    public boolean deleteOrder(String orderId, Integer userId) {

        logger = LoggerFactory.getLogger(OrderinfoServiceImpl.class);

        QueryWrapper<Orderinfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("order_id",orderId);
        // 查询出来我们要退订的机票后
        logger.info("开始查询要退订的订单");
        Orderinfo orderinfo = orderinfoMapper.selectOne(queryWrapper);
        //开始校验机票时间是否已经超过了当前时间（稍后进行补充，同时前端部分也要进行时间的校验进行拦截）
        logger.info("进行订单失效时间的判断");
        // 进行订单状态的改变
        logger.info("开始进行订单状态的改变，更新为已退定");
        UpdateWrapper<Orderinfo> updateWrapper = new UpdateWrapper<>();
        // 更新已经退订的订单状态为3
        updateWrapper.set("status",3);

        int update = orderinfoMapper.update(null, updateWrapper);

        if (update > 0){

            return true;

        }

        return false;
    }


}
