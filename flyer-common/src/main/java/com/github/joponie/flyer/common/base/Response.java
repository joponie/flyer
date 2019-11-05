package com.github.joponie.flyer.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 刘杰鹏
 * @since 2019-11-04
 */
@NoArgsConstructor
@Data
public class Response<T> implements Serializable {

    private static final int SUCCESS = 200;
    private static final int FAIL = 500;
    private static final long serialVersionUID = 1L;

    private String msg = "success";
    private int code = SUCCESS;
    private T data;

    public Response(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public Response(Throwable e) {
        super();
        this.msg = e.getMessage();
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
}
