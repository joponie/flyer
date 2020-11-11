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

    SYS_TOO_BUSY("1001", "系统太忙啦，请稍后"),
    INVALID_REQUEST_FORMAT("1001", "请求格式错误");

    private final String code;
    private final String message;
}
