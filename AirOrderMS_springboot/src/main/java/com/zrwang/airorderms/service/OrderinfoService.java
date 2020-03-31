package com.zrwang.airorderms.service;

import com.zrwang.airorderms.entity.Orderinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrwang.airorderms.entity.dto.CreateOrderInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */
public interface OrderinfoService extends IService<Orderinfo> {

     boolean createOrder(Orderinfo orderinfo);

}
