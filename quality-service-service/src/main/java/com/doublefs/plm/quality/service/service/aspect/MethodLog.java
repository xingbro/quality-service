package com.doublefs.plm.quality.service.service.aspect;

import java.lang.annotation.*;

/**
 * @author wujin@doublefs.com
 * @date 2021/11/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MethodLog {

}