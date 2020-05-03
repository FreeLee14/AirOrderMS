package com.zrwang.airorderms.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class TourismDetailView {

    private Integer id;

    /**
     * 旅游信息表id
     */
    private Integer tourismId;

    /**
     * 旅游景点详情描述
     */
    private String detailDescription;

    /**
     * 旅游详情图片名称
     */
    private List<String> detailImages;
}
