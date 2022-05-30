package com.doublefs.plm.quality.service.data.utils.sys;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import com.doublefs.plm.quality.service.data.common.exception.BusException;

/**
 * 业务异常断言 断言某些对象或值是否符合规定，否则抛出业务异常，用于提醒业务操作不规范。
 *
 * @author wujin@doublefs.com
 * @date 2022/1/8
 */
public class AssertBus {

    private AssertBus() {
        throw new IllegalStateException("AssertBus class");
    }

    /**
     * 断言是否为真，如果为 {@code false} 抛出 {@code BusException} 异常，业务异常不会报警<br>
     *
     * @param expression       布尔值
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params           参数列表
     * @throws IllegalArgumentException if expression is {@code false}
     */
    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) throws BusException {
        Assert.isTrue(expression, () -> new BusException(CharSequenceUtil.format(errorMsgTemplate, params)));
    }


    /**
     * 断言是否为假，如果为 {@code true} 抛出 {@code BusException} 异常，业务异常不会报警<br>
     *
     * @param expression       布尔值
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params           参数列表
     * @throws IllegalArgumentException if expression is {@code false}
     */
    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) throws BusException {
        Assert.isFalse(expression, () -> new BusException(CharSequenceUtil.format(errorMsgTemplate, params)));
    }


    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出{@link BusException} 异常，业务异常不会报警} .
     *
     * @param <T>              被检查对象泛型类型
     * @param object           被检查对象
     * @param errorMsgTemplate 错误消息模板，变量使用{}表示
     * @param params           参数
     * @return 被检查后的对象
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static <T> T notNull(T object, String errorMsgTemplate, Object... params) throws BusException {
        return Assert.notNull(object, () -> new BusException(CharSequenceUtil.format(errorMsgTemplate, params)));
    }

}
