package com.huawei.vsp.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述
 *
 * @since 2020-08-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SitcomRelation implements Serializable {
    private static final long serialVersionUID = -4119705658800524925L;

    /**
     * 父集编号。
     */
    private long sitcomId;
    /**
     * 集号。
     */
    private int serialNo;

    /**
     * 子集编号。
     */
    private long itemId;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 0：正本 1：副本
     */
    private int changeFlag = 0;
}
