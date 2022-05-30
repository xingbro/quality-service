package com.doublefs.plm.quality.service.web.conf;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.doublefs.plm.quality.service.web.injector.SqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: mybatis拦截器
 *
 * @author 吴瑾 (wujin@doublefs.com)
 * @date 2021-11-10 17:51
 */
@Configuration
public class MybatisConfig {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * Ps：3.4.X以后应该不需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 防止全局数据更新
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 自定义的mybatis sql注册器
     *
     * @return
     */
    @Bean
    public SqlInjector sqlInjector() {
        return new SqlInjector();
    }
}
