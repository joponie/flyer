package com.github.joponie.flyer.common.base;

import com.github.joponie.flyer.common.exception.FlyerException;
import com.github.joponie.flyer.common.exception.IExceptionEnums;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kain
 * @since 2019-11-04
 */
@NoArgsConstructor
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "200";
    private static final String FAIL = "500";

    private String message = "success";
    private String code = SUCCESS;
    private T data;

    public Response(T data, String message) {
        super();
        this.data = data;
        this.message = message;
    }

    public Response(Throwable e) {
        super();
        this.message = e.getMessage();
        this.code = FAIL;
    }

    public static Response<Boolean> rest(boolean result) {
        Response<Boolean> r = new Response<>();
        if (!result) {
            r.setCode(Response.FAIL);
            r.setData(false);
        }
        return r;
    }

    public static Response<Boolean> ok() {
        Response<Boolean> r = new Response<>();
        r.setCode(Response.SUCCESS);
        r.setData(true);
        return r;
    }

    public static <T> Response<T> of(T t) {
        Response<T> r = new Response<>();
        r.setCode(Response.SUCCESS);
        r.setData(t);
        return r;
    }

    public static Response<Boolean> ofEx(FlyerException e) {
        return ofEx(e.getCode(), e.getMessage());
    }

    public static Response<Boolean> ofEx(IExceptionEnums e) {
        return ofEx(e.getCode(), e.getMessage());
    }

    public static Response<Boolean> ofEx(String code, String message) {
        Response<Boolean> response = new Response<>();
        response.setData(Boolean.FALSE);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
