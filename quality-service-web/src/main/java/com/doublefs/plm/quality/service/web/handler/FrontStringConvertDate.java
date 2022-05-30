package com.doublefs.plm.quality.service.web.handler;

import cn.hutool.core.date.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 前端String格式的日期，转为Date
 *
 * @author wujin@doublefs.com
 * @date 2021/11/28
 */
public class FrontStringConvertDate implements Converter<String, Date> {

    /**
     * 支持的前端入参格式为
     *
     * @param source 前端入参的字符串日期
     * @return
     */
    @Override
    public Date convert(String source) {
        return DateUtil.parse(source);
    }
}
