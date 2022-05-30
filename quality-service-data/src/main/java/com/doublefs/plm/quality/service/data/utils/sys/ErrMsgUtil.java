package com.doublefs.plm.quality.service.data.utils.sys;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.doublefs.plm.quality.service.data.utils.user.ReqHeadUtil;

import java.util.Optional;

/**
 * @author wujin@doublefs.com
 * @date 2021/11/18
 */
public class ErrMsgUtil {

    private ErrMsgUtil() {
    }

    /**
     * 为了让sentry可以打印出堆栈异常信息，将堆栈信息作为log.error()的msg.
     *
     * @param e                 异常
     * @param kibinaMarkHeadMsg 作为kibina搜索的开头关键字
     */
    public static String getSentryMsg(String kibinaMarkHeadMsg, Exception e) {
        return Optional.ofNullable(kibinaMarkHeadMsg).orElse("错误日志信息")
                + CharSequenceUtil.format(" 操作人：{}，操作人Id：{}\t\n堆栈信息：{}", ReqHeadUtil.getRequestUserName(),
                ReqHeadUtil.getDfsUserId(), ExceptionUtil.stacktraceToString(e));
    }
}
