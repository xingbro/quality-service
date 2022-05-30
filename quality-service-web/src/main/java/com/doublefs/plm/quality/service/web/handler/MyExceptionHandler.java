package com.doublefs.plm.quality.service.web.handler;

import com.doublefs.plm.quality.service.data.common.ResponseData;
import com.doublefs.plm.quality.service.data.common.exception.BusException;
import com.doublefs.plm.quality.service.data.common.exception.SysException;
import com.doublefs.plm.quality.service.data.utils.sys.ErrMsgUtil;
import io.sentry.SentryLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author wujin@doublefs.com
 * @date 2021/11/16
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.doublefs.plm.pattern.service"})
@ResponseBody
public class MyExceptionHandler {

    @ExceptionHandler({BusException.class})
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, BusException e) {
        log.warn(ErrMsgUtil.getSentryMsg("pattern-service业务异常信息", e), SentryLevel.WARNING);
        return ResponseData.fail(e.getMessage());
    }

    @ExceptionHandler({SysException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, SysException e) {
        log.error(ErrMsgUtil.getSentryMsg("pattern-service系统异常信息", e));
        return ResponseData.fail(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        String var10000 = fieldError.getField();
        String msg = fieldError.getDefaultMessage();
        log.error(ErrMsgUtil.getSentryMsg("pattern-service方法入参有误", e));
        return ResponseData.fail(msg);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, IllegalArgumentException e) {
        log.error(ErrMsgUtil.getSentryMsg("pattern-service数据校验失败：", e));
        return ResponseData.fail(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        log.error(ErrMsgUtil.getSentryMsg("pattern-service数据格式不对：", e));
        return ResponseData.fail("数据格式不对，请检查");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, Exception e) {
        log.error(ErrMsgUtil.getSentryMsg("pattern-service兜底错误信息", e));
        return ResponseData.fail("系统异常,请联系管理员");
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, BindException c) {
        // 处理返回的错误信息
        StringBuilder errorMsg = new StringBuilder();
        List<ObjectError> errors = c.getBindingResult().getAllErrors();
        for (ObjectError error : errors) {
            errorMsg.append(error.getDefaultMessage()).append(";");
        }
        log.error(ErrMsgUtil.getSentryMsg("pattern-service数据绑定问题", c));
        return ResponseData.fail(errorMsg.toString());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, HttpMessageNotReadableException e) {
        log.error(ErrMsgUtil.getSentryMsg("pattern-service参数类型不对", e));
        return ResponseData.fail("参数类型不对，请检查");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData<Object> exception(HttpServletRequest request, MissingServletRequestParameterException e) {
        log.error(ErrMsgUtil.getSentryMsg("pattern-service入参缺失", e));
        return ResponseData.fail("入参缺失，请检查");
    }

}
