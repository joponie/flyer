package com.github.joponie.flyer.common.exception;

import lombok.Data;

/**
 * @author 刘杰鹏
 * @since 2019-11-05
 */
@Data
public class FlyerException extends RuntimeException {

    private String code;

    private String message;

    public FlyerException(String code, String message) {
        super(code + message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.format("flyer exception error code %s, error message %s", code, message);
    }
}
