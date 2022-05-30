package com.doublefs.plm.quality.service.data.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wujin@doublefs.com
 * @date 2021/11/22
 */
@Data
public class BasePageParam implements Serializable {

    private static final long serialVersionUID = -7617142381061020718L;

    /**
     * 页数
     */
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 20;
}
