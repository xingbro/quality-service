package com.doublefs.plm.quality.service.web.handler;

import com.doublefs.plm.quality.service.data.common.ReqHead;
import com.doublefs.plm.quality.service.data.utils.user.ReqHeadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * 系统拦截
 *
 * @author chickenman
 */
public class SysInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) {
        try {
            // 请求头数据
            ReqHead reqHead = new ReqHead();
            String uuid = Optional.ofNullable(request.getHeader("track-id")).orElse("");
            MDC.put("trackId", uuid);
            reqHead.setAppId(Long.valueOf(Optional.ofNullable(request.getHeader("app-id")).orElse("10")));
            reqHead.setCurrency(Optional.ofNullable(request.getHeader("currency")).orElse("USD"));
            reqHead.setLang(Optional.ofNullable(request.getHeader("lang")).orElse("zh-CN"));
            reqHead.setSource(Optional.ofNullable(request.getHeader("system-source")).orElse("H5"));
            reqHead.setTrackId(uuid);
            reqHead.setUserId(Long.valueOf(Optional.ofNullable(request.getHeader("x-user-id")).orElse("0")));
            reqHead.setUserName(URLDecoder.decode(
                    Optional.ofNullable(request.getHeader("x-user-name")).orElse("system"), StandardCharsets.UTF_8));
            ReqHeadUtil.setReqHead(reqHead);
        } catch (IllegalArgumentException e) {
            LOGGER.error("获取用户信息参数有误", e);
        } catch (Exception e) {
            LOGGER.error("获取用户信息报错", e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        ReqHeadUtil.removeReqHead();
    }
}
