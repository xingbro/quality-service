package com.doublefs.plm.quality.service.data.utils.user;

import cn.hutool.core.text.CharSequenceUtil;
import com.doublefs.plm.quality.service.data.common.ReqHead;

/**
 * 登录用户操作工具类
 *
 * @author chickenman
 */
public class ReqHeadUtil {

    /**
     * 请求头数据
     */
    private static final ThreadLocal<ReqHead> REQ_HEAD_THREAD_LOCAL = new ThreadLocal<>();

    private ReqHeadUtil() {
    }

    /**
     * 获取请求头
     */
    public static ReqHead getReqHead() {
        return REQ_HEAD_THREAD_LOCAL.get();
    }

    public static void setReqHead(ReqHead reqHead) {
        REQ_HEAD_THREAD_LOCAL.set(reqHead);
    }

    public static void removeReqHead() {
        REQ_HEAD_THREAD_LOCAL.remove();
    }

    /**
     * 获取登录的用户姓名
     */
    public static String getRequestUserName() {
        ReqHead requestUser = getReqHead();
        if (requestUser == null || CharSequenceUtil.isBlank(requestUser.getUserName())) {
            return "system";
        }
        return requestUser.getUserName();
    }

    /**
     * 获取dfs用户id
     */
    public static Long getDfsUserId() {
        ReqHead requestUser = getReqHead();
        if (requestUser == null) {
            return 0L;
        }
        return requestUser.getUserId();
    }

}