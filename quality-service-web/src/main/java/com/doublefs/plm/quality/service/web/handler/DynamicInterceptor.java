package com.doublefs.plm.quality.service.web.handler;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.doublefs.plm.quality.service.data.common.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Properties;

/**
 * 多数据源自动切换,如果是查询就走从库，否则走主库
 * 如果要显示的走对应的库可以通过在方法上使用 @DS("master")//标明使用master数据源
 *
 * @author wujin@doublefs.com
 * @date 2021/11/19
 */

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@Slf4j
@Component
public class DynamicInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //是否为事务，非事务情况才走主从分离
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];
            String currentDataSource = DynamicDataSourceContextHolder.peek();
            if (StringUtils.isEmpty(currentDataSource)) {
                if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    DynamicDataSourceContextHolder.push(CommonConstants.DynamicSourceName.SLAVE);
                } else {
                    DynamicDataSourceContextHolder.push(CommonConstants.DynamicSourceName.MASTER);
                }
                Object proceed = invocation.proceed();
                DynamicDataSourceContextHolder.clear();
                return proceed;
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
