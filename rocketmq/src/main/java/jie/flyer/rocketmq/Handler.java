package jie.flyer.rocketmq;

/**
 * @author kain
 * @since 2019-11-28
 */
@FunctionalInterface
public interface Handler<T> {
    /**
     * Accept.
     *
     * @param t the t
     * @throws Exception the exception
     */
    void handle(T t) throws Exception;
}