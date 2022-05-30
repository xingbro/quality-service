package com.doublefs.plm.quality.service.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * @author wujin@doublefs.com
 * @date 2022/1/6
 */
public interface AaBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量新增数据,自选字段 insert
     * 自己的通用 mapper 如下使用:
     * int insertBatchSomeColumn(List  entityList);
     * 注意: 这是自选字段 insert !!,如果个别字段在 entity 里为 null 但是数据库中有配置默认值, insert 后数据库字段是为 null 而不是默认值
     * 所有如果有默认值需要手动赋值(除了MyMetaObjHandler配置的字段)
     *
     * @param entityList
     * @return
     */
    int insertBatchSomeColumn(Collection<T> entityList);
}
