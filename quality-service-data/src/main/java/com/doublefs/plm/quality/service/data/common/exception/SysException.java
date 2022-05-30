package com.doublefs.plm.quality.service.data.common.exception;

/**
 * 商品中心系统异常
 *
 * @author wujin@doublefs.com
 * @date 2021/11/16
 */
public class SysException extends RuntimeException {
    public SysException(String message) {
        super(message);
    }
}
