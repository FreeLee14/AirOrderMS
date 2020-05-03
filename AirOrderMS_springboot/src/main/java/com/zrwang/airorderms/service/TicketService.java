package com.zrwang.airorderms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrwang.airorderms.entity.Ticket;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrwang.airorderms.entity.vo.NominateTicket;
import com.zrwang.airorderms.entity.dto.TicketConditions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */
public interface TicketService extends IService<Ticket> {

    /**
     * 推荐机票的接口
     * @param departure
     * @return
     */
    List<NominateTicket> findRandom(String departure);

    /**
     * 根据复合条件进行精准查询机票信息
     * @param conditions
     * @return
     */
    List<Ticket> findByConditions(TicketConditions conditions);

    /**
     * 实现分页查询所有的机票信息
     * @param currentPage
     * @param pageCounts
     * @return
     */
    Map<String,Object> showAllByPage(Integer currentPage, Integer pageCounts);

    /**
     * 更新机票状态
     * @param id
     */
    void updateTicket(AtomicReference<Integer> id ,Integer status);
}
