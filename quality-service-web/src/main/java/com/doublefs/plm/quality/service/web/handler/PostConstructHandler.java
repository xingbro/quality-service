package com.doublefs.plm.quality.service.web.handler;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author wujin@doublefs.com
 * @date 2021/11/28
 */
@Component
public class PostConstructHandler {
    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

}
