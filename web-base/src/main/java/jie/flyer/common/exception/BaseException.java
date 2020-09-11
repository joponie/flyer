package jie.flyer.common.exception;

import lombok.Data;

/**
 * @author kain
 * @since 2019-11-05
 */
@Data
public class BaseException extends RuntimeException {

    private String code;

    private String message;

    public BaseException(String code, String message) {
        super(String.format("code:[%s] message:[%s]", code, message));
        this.code = code;
        this.message = message;
    }

    public BaseException(IExCodeEnum codeEnum) {
        super(String.format("code:[%s] message:[%s]", codeEnum.getCode(), codeEnum.getMessage()));
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    @Override
    public String getMessage() {
        return String.format("flyer exception error code %s, error message %s", code, message);
    }
}
