package com.doublefs.plm.quality.service.data.utils.sys;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author chaos
 * @description
 * @date created in 2021/8/10 19:26
 * @modified
 */
@Configuration
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 获取当前环境
     *
     * @return
     */
    public static String getActiveProfile() {
        return ApplicationContextUtil.getContext().getEnvironment().getActiveProfiles()[0];
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.context = applicationContext;
    }

}
