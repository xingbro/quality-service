package com.doublefs.plm.quality.service.web.conf;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient getRedisson(RedisProperties redisProperties) {
        String address = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();
        Config config = new Config();
        // 单机模式依次设置redis地址和密码
        config.useSingleServer().setAddress(address);
        return Redisson.create(config);
    }
}
