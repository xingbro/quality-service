package com.doublefs.plm.quality.service.web.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @Author cxw
 * @Version 1.0
 * @date 2021/11/30 11:05
 * @ClassName RequestContextConfig
 * @Description
 */
@Component
public class RequestContextConfig {

    /**
     * 监听器：监听HTTP请求事件
     * 解决RequestContextHolder.getRequestAttributes()空指针问题
     *
     * @return
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
