package com.huawei.vsp.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VodUserGroup
 *
 * @since 2020-08-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VodUserGroup implements Serializable {
    private static final long serialVersionUID = -3438542177992942373L;

    /**
     * mediaId默认值为-1， 表示这个用户分组信息是vod的.
     */
    public static final String VOD_USER_GROUP_FLAG = "-1";


    private long vodId;

    /**
     * mediaId默认值为-1， 表示这个用户分组信息是vod的
     */
    private String mediaId = "-1";

    /**
     * 用户分组ID
     */
    private String groupId;

    private String updateTime;
}
