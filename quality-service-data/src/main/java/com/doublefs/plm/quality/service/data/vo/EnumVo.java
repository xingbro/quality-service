package com.doublefs.plm.quality.service.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 枚举值对应实体，就是 key 对 value
 *
 * @author liuxing@doublefs.com
 * @date 2021/12/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnumVo implements Serializable {

    private static final long serialVersionUID = 7823190088225246513L;

    /**
     * 枚举状态值
     */
    private Integer type;

    /**
     * 枚举描述
     */
    private String name;
}
