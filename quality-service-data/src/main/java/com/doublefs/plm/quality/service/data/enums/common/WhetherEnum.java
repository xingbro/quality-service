package com.doublefs.plm.quality.service.data.enums.common;

import com.doublefs.plm.quality.service.data.common.exception.BusException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author cxw
 * @Version 1.0
 * @date 2021/11/25 20:24
 * @ClassName WhetherEnum
 * @Description 是/否枚举
 */
public enum WhetherEnum {
    NO(1, "否"),
    YES(2, "是");

    private static final Map<Integer, WhetherEnum> data = new ConcurrentHashMap<>();

    static {
        for (WhetherEnum item : WhetherEnum.values()) {
            data.put(item.getStatus(), item);
        }
    }

    private Integer status;
    private String desc;

    WhetherEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static WhetherEnum getEnum(Integer type) {
        WhetherEnum whetherEnum = data.get(type);
        if (whetherEnum == null) {
            throw new BusException("任务状态非法，请查证");
        }
        return whetherEnum;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
