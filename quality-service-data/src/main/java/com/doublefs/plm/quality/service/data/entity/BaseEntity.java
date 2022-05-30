package com.doublefs.plm.quality.service.data.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * description:
 *
 * @author 吴瑾 (wujin@doublefs.com)
 * @date 2021-04-27 16:47
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -3603728840377989726L;

    /**
     * 是否被删除, 2不是，其他都认为被删除了，为了使逻辑删除作为索引，默认为null
     */
    @TableLogic(value = "2", delval = "null")
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createdUserId;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdUserName;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updatedUserId;


    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedUserName;
}
