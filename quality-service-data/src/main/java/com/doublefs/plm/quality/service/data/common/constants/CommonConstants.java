package com.doublefs.plm.quality.service.data.common.constants;

import java.math.BigDecimal;

/**
 * 公共使用
 *
 * @author wujin@doublefs.com
 * @date 2021/11/19
 */
public class CommonConstants {

    private CommonConstants() {
        throw new IllegalStateException("CommonConstants class");
    }

    /**
     * 读写分离，主从库配置名字
     */
    public static class DynamicSourceName {
        public static final String MASTER = "master";
        public static final String SLAVE = "slave";

        private DynamicSourceName() {
            throw new IllegalStateException("DynamicSourceName class");
        }
    }

    /**
     * 环境配置
     */
    public static class ActiveProfileConstants {
        private ActiveProfileConstants() {
            throw new IllegalStateException("ActiveProfileConstants class");
        }
        public static final String DEV = "dev";
        public static final String QA = "qa";
        public static final String PROD = "prod";
    }

    /**
     * 日常参数配置
     */
    public static class DataConstants{
        private DataConstants() {
            throw new IllegalStateException("DataConstants class");
        }
        public static final BigDecimal FIRST_VERSION_NUM = new BigDecimal("1.0");
        public static final BigDecimal VERSION_NUM_INCREASING = new BigDecimal("0.1");
    }

}
