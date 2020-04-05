package com.zrwang.airorderms.service;

import com.zrwang.airorderms.entity.Orderinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrwang.airorderms.entity.dto.CreateOrderInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */
public interface OrderinfoService extends IService<Orderinfo> {
     /**
      * 创建订单的接口
      * @param orderinfo
      * @return
      */
     boolean createOrder(Orderinfo orderinfo);

     /**
      * 确认订单的接口
      * @param orderId
      * @return
      */
     boolean ensureOrder(String orderId, Integer status,Integer ticketId);

     /**
      * 自动随机出票并更新订单方法，给订单方法中添加座位信息
      * @param ticketId
      * @param staus
      * @return
      */
     String updateOrder(Integer ticketId, Integer staus);

     /**
      * 查询所有订单方法
      * @param userId
      * @return
      */
     List<Orderinfo> findAllOrder(Integer userId);

     /**
      * 退订业务
      * @param userId
      * @param orderId
      * @return
      */
     boolean deleteOrder(String orderId, Integer userId);
}
