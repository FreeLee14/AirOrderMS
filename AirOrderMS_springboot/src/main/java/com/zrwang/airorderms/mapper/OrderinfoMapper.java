package com.zrwang.airorderms.mapper;

import com.zrwang.airorderms.entity.Orderinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zrwang.airorderms.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zrwang
 * @since 2020-03-13
 */
@Repository
@Mapper
public interface OrderinfoMapper extends BaseMapper<Orderinfo> {

    @Select("SELECT t.flight AS flight , t.departure AS departure, t.destination AS destination " +
            "FROM orderinfo o , ticket t , `user` u " +
            "WHERE o.user_id = u.id AND o.ticket_id = t.id AND u.id = #{userId}")
    List<Ticket> findMyRoute (Integer userId);
}
