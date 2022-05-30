package com.doublefs.plm.quality.service.service.aspect;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 日志切面
 *
 * @author wujin@doublefs.com
 * @date 2021/11/28
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * PrintLog切点，用于打印加了该注解的方法出入参
     */
    @Pointcut("@annotation(com.doublefs.plm.quality.service.service.aspect.MethodLog)")
    public void methodLog() {
    }

    /**
     * controller 层切点，用于打印前后端入参及出参
     */
    @Pointcut("execution(* com.doublefs.plm.pattern.service.web.controller..*.*(..))")
    public void controllerPointcut() {
    }

    /**
     * @MethodLog 注解的日志打印
     */
    @Around("methodLog()")
    public Object printLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature sig = (MethodSignature) joinPoint.getSignature();
        String methodStr = sig.getDeclaringType().getSimpleName() + "." + sig.getName();
        StringBuilder params = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        Object[] objects = joinPoint.getArgs();
        for (int i = 0; i < objects.length; i++) {
            params.append(" parm[").append(i).append("]:").append(mapper.writeValueAsString(objects[i]));
        }
        log.info("方法:{},入参:{}", methodStr, params);
        Object result = joinPoint.proceed();
        log.info("方法:{},出参:{}", methodStr, Optional.ofNullable(result).map(JSONUtil::toJsonStr).orElse(""));
        return result;
    }

    /**
     * controller 层出入参日志记录
     *
     * @param joinPoint 切点
     * @return
     */
    @Around(value = "controllerPointcut()")
    public Object controllerLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取 request 中包含的请求参数, TODO  已在 RequestContextConfig类中定义监听器避免空指针，但是这里的sonar还报空指针，待解决
        if (RequestContextHolder.getRequestAttributes() == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取切点请求参数(class,method)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        StringBuilder params = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
            Object[] objects = joinPoint.getArgs();
            for (Object arg : objects) {
                //HttpServletResponse 不需要记录
                if(arg instanceof HttpServletResponse){
                   continue;
                }
                params.append(mapper.writeValueAsString(arg));
            }
        } else if (HttpMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
            params.append(request.getQueryString());
        }
        // 调用类及对应的方法
        String methodStr = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        log.info("请求:{},方法:{}，入参:{}", request.getRequestURL(), methodStr, params);
        Object result = joinPoint.proceed();
        log.info("请求:{},方法:{}，出参:{}", request.getRequestURL(), methodStr,
                Optional.ofNullable(result).map(JSONUtil::toJsonStr).orElse(""));
        return result;
    }
}
