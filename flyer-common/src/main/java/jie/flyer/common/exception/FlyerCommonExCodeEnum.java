package jie.flyer.common.exception;

import lombok.AllArgsConstructor;

/**
 * @author kain
 * @since 2019-11-05
 */
@AllArgsConstructor
public enum FlyerCommonExCodeEnum implements IExceptionEnums {

    INVALID_REQUEST_FORMAT("1005", "请求格式错误"),
    SYS_TOO_BUSY("1006", "系统太忙啦，请稍后");

    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
