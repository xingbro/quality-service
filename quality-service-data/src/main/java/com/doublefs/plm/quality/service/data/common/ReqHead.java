package com.doublefs.plm.quality.service.data.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求头数据
 *
 * @author wujin@doublefs.com
 * @date 2021/11/16
 */
@Data
public class ReqHead implements Serializable {
    private static final long serialVersionUID = 8416959979694442454L;
    /**
     * 飞书用户id
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;

    private Long appId;

    /**
     * 语言
     */
    private String lang;

    /**
     * 货币
     */
    private String currency;
    private String source;
    private String trackId;
}
