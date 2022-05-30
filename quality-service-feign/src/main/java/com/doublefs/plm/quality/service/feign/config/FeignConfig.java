package com.doublefs.plm.quality.service.feign.config;

import cn.hutool.core.net.URLEncoder;
import cn.hutool.core.util.CharsetUtil;
import com.doublefs.plm.quality.service.data.utils.user.ReqHeadUtil;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;

/**
 * @author liuxing@doublefs.com
 * @date 2021/11/24
 */
public class FeignConfig implements RequestInterceptor {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("x-user-id", ReqHeadUtil.getDfsUserId() + "");
        requestTemplate.header("x-user-name", URLEncoder.createDefault().encode(ReqHeadUtil.getRequestUserName(), CharsetUtil.CHARSET_UTF_8));
    }

    @Bean
    public feign.Logger logger() {
        return new Slf4jLogger();
    }

}
