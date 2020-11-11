package jie.flyer.common.exception;

import lombok.Data;

/**
 * @author kain
 * @since 2019-11-05
 */
@Data
public class BizException extends RuntimeException {

    private String code;

    private String message;

    public BizException(String code, String message) {
        super(String.format("code:[%s] message:[%s]", code, message));
        this.code = code;
        this.message = message;
    }

    public BizException(IExCodeEnum codeEnum) {
        super(String.format("code:[%s] message:[%s]", codeEnum.getCode(), codeEnum.getMessage()));
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    @Override
    public String getMessage() {
        return String.format("flyer exception error code %s, error message %s", code, message);
    }
}
