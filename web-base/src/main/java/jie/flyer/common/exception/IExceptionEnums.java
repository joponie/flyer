package jie.flyer.common.exception;

/**
 * @author kain
 * @since 2019-11-05
 */
public interface IExceptionEnums {
    /**
     * 断言编码
     */
    String getCode();

    /**
     * 断言消息
     */
    String getMessage();

    default BaseException getException() {
        return new BaseException(getCode(), getMessage());
    }
}
