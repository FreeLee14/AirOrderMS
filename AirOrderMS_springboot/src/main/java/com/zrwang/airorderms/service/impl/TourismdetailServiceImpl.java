package com.zrwang.airorderms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrwang.airorderms.entity.Tourismdetail;
import com.zrwang.airorderms.entity.Tourisminfo;
import com.zrwang.airorderms.entity.vo.TourismDetailView;
import com.zrwang.airorderms.mapper.TourismdetailMapper;
import com.zrwang.airorderms.service.TourismdetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.naming.Name;
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
public class TourismdetailServiceImpl extends ServiceImpl<TourismdetailMapper, Tourismdetail> implements TourismdetailService {

    @Autowired
    private TourismdetailMapper tourismdetailMapper;

    /**
     * 查看旅游信息详情的实现方法
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheManager = "redisCacheManager" , cacheNames = "tourismDetail",key = "#id")
    public Map<String,Object> showDetail(String id) {
        // 初始化结果
        TourismDetailView tourismDetailView = new TourismDetailView();
        Map<String,Object> res = new HashMap<>();
        QueryWrapper<Tourismdetail> queryWrapper = new QueryWrapper<>();
        if(id != null && id != "0")
            queryWrapper.eq("tourism_id",id);
        else
            return null;

        Tourismdetail tourismdetail = tourismdetailMapper.selectOne(queryWrapper);
        // 此处只是用来存储数据，所以用链表结构的容器
        List<String> detail_images = new LinkedList<>();
        // 获取到多个图片名称的拼接字符串
        String detailImages = tourismdetail.getDetailImages();
        if (detailImages != null && detailImages.length() > 0){
            // 拆分为数组
            String[] split = detailImages.split(",");
            for (String item : split){
                detail_images.add(item);
            }
        }

        tourismDetailView.setId(tourismdetail.getId());
        tourismDetailView.setTourismId(tourismdetail.getTourismId());
        tourismDetailView.setDetailDescription(tourismdetail.getDetailDescription());
        tourismDetailView.setDetailImages(detail_images);
        res.put("tourismDetailView",tourismDetailView);
        return res;
    }
}
