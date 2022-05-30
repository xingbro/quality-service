package com.doublefs.plm.quality.service.data.common.exception;

/**
 * 商品中心业务异常
 *
 * @author wujin@doublefs.com
 * @date 2021/11/16
 */
public class BusException extends RuntimeException {
    public BusException(String message) {
        super(message);
    }
}
