package jie.flyer.common.base;

import jie.flyer.common.exception.BaseException;
import jie.flyer.common.exception.IExCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kain
 * @since 2019-11-04
 */
@NoArgsConstructor
@Data
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "200";
    private static final String FAIL = "500";

    private String message = "success";

    private String code = SUCCESS;

    private Object data;

    public Response(Object data, String message) {
        super();
        this.data = data;
        this.message = message;
    }

    public Response(Throwable e) {
        super();
        this.message = e.getMessage();
        this.code = FAIL;
    }

    public static Response rest(boolean result) {
        Response r = new Response();
        if (!result) {
            r.setCode(Response.FAIL);
            r.setData(false);
        }
        return r;
    }

    public static Response ok() {
        Response r = new Response();
        r.setCode(Response.SUCCESS);
        r.setData(true);
        return r;
    }

    public static <T> Response ok(T t) {
        Response r = new Response();
        r.setCode(Response.SUCCESS);
        r.setData(t);
        return r;
    }

    public static Response ofEx(BaseException e) {
        return ofEx(e.getCode(), e.getMessage());
    }

    public static Response ofEx(IExCodeEnum e) {
        return ofEx(e.getCode(), e.getMessage());
    }

    public static Response ofEx(String code, String message) {
        Response response = new Response();
        response.setData(Boolean.FALSE);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
