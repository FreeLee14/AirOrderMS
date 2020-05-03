package com.zrwang.airorderms.controller;


import com.zrwang.airorderms.entity.vo.TourismDetailView;
import com.zrwang.airorderms.service.TourismdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zrwang
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/airorderms")
@CrossOrigin
public class TourismdetailController {

    @Autowired
    private TourismdetailService tourismdetailService;

    @GetMapping("/tourismdetail")
    public Map<String,Object> showDetail(String ID){

        Map<String,Object> res = tourismdetailService.showDetail(ID);

        return res;
    }

}

