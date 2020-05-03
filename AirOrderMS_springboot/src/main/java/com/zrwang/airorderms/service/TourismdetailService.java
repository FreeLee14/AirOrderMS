package com.zrwang.airorderms.service;

import com.zrwang.airorderms.entity.Tourismdetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrwang.airorderms.entity.Tourisminfo;
import com.zrwang.airorderms.entity.vo.TourismDetailView;

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
public interface TourismdetailService extends IService<Tourismdetail> {

    Map<String,Object> showDetail(String id);
}
