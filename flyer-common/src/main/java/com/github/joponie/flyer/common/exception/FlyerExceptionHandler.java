package com.github.joponie.flyer.common.exception;

import com.github.joponie.flyer.common.base.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * @author 刘杰鹏
 * @since 2019-11-05
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class FlyerExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MissingServletRequestParameterException.class, HttpMessageNotReadableException.class
            , MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseBody
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return Response.ofEx(FlyerCommonExCodeEnum.INVALID_REQUEST_FORMAT);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response systemException(Exception e) {
        log.error("系统错误", e);
        return Response.ofEx(FlyerCommonExCodeEnum.SYS_TOO_BUSY);
    }

    @ExceptionHandler(FlyerException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response flyerException(FlyerException e) {
        return Response.ofEx(e);
    }

}