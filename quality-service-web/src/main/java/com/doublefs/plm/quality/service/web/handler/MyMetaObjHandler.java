package com.doublefs.plm.quality.service.web.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.doublefs.plm.quality.service.data.utils.user.ReqHeadUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * description:对数据库每条记录的创建时间和更新时间自动进行填充
 *
 * @author 吴瑾 (wujin@doublefs.com)
 * @date 2021-11-16
 */
@Component
public class MyMetaObjHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "createdAt", Date.class, DateUtil.date());
        this.strictInsertFill(metaObject, "createdUserId", Long.class, ReqHeadUtil.getDfsUserId());
        this.strictInsertFill(metaObject, "createdUserName", String.class, ReqHeadUtil.getRequestUserName());
        this.strictInsertFill(metaObject, "updatedAt", Date.class, DateUtil.date());
        this.strictInsertFill(metaObject, "updatedUserId", Long.class, ReqHeadUtil.getDfsUserId());
        this.strictInsertFill(metaObject, "updatedUserName", String.class, ReqHeadUtil.getRequestUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updatedAt", Date.class, DateUtil.date());
        this.strictInsertFill(metaObject, "updatedUserId", Long.class, ReqHeadUtil.getDfsUserId());
        this.strictInsertFill(metaObject, "updatedUserName", String.class, ReqHeadUtil.getRequestUserName());
    }

}
