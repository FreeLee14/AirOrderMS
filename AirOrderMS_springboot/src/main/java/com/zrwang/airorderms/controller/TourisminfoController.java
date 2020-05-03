package com.zrwang.airorderms.controller;


import com.zrwang.airorderms.entity.Tourismdetail;
import com.zrwang.airorderms.entity.Tourisminfo;
import com.zrwang.airorderms.service.TourismdetailService;
import com.zrwang.airorderms.service.TourisminfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
public class TourisminfoController {

    @Autowired
    private TourisminfoService tourisminfoService;

    @GetMapping("/tourisminfo/{id}/{currentPage}/{pageCounts}")
    public Map<String,Object> showAllTourism(@PathVariable (value = "id") Integer id,
                                             @PathVariable(value = "currentPage") Integer currentPage,
                                             @PathVariable(value = "pageCounts") Integer pageCounts){

        Map<String, Object> res = tourisminfoService.showAllTourism(id, currentPage, pageCounts);

        return res;
    }
}

