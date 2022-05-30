package com.doublefs.plm.quality.service.web;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author liuzhaoya
 * @date 2022/5/26
 */
@SpringBootApplication(scanBasePackages = "com.doublefs.*")
@EnableAsync
@EnableRetry
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableFeignClients(basePackages = {"com.doublefs.plm.quality.service.feign"})
@MapperScan(basePackages = "com.doublefs.plm.quality.service.data.mapper")
@EnableLogRecord(tenant = "com.doublefs.plm.quality.service.")
public class QualityServiceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QualityServiceWebApplication.class, args);
    }

}
