package jie.flyer.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kain
 * @since 2019-11-05
 */
@Getter
@AllArgsConstructor
public enum BaseExCodeEnum implements IExCodeEnum {

    INVALID_REQUEST_FORMAT("1005", "请求格式错误"),
    SYS_TOO_BUSY("1006", "系统太忙啦，请稍后");

    private final String code;
    private final String message;
}
