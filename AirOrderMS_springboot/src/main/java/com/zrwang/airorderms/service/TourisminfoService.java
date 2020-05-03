package com.zrwang.airorderms.service;

import com.zrwang.airorderms.entity.Tourisminfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zrwang
 * @since 2020-04-28
 */
public interface TourisminfoService extends IService<Tourisminfo> {
    public Map<String,Object> showAllTourism(Integer Id, Integer currentPage, Integer pageCounts);
}
