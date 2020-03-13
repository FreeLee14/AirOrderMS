package com.zrwang.airorderms.mapper;

import com.zrwang.airorderms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zrwang
 * @since 2020-03-09
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
