package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrwang.airorderms.entity.Orderinfo;
import com.zrwang.airorderms.entity.Ticket;
import com.zrwang.airorderms.entity.dto.CreateOrderInfo;
import com.zrwang.airorderms.mapper.OrderinfoMapper;
import com.zrwang.airorderms.mapper.TicketMapper;
import com.zrwang.airorderms.service.OrderinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
    OrderinfoMapper orderinfoMapper;
    @Autowired
    TicketMapper ticketMapper;

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
        // 判断获取的机票起飞时间是否为空，如果为空那么就不进行转型
        if (ticket.getStartTime() != null){

            Timestamp invalidTimestamp = ticket.getStartTime();
            // 将获取到的时间戳转型为date格式，并转换为我们定义的对应格式字符串
            invalidTime = simpleDateFormat.format(new Date(invalidTimestamp.getTime()));

        }

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
}
