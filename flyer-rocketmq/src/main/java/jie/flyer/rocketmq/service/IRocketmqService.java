package jie.flyer.rocketmq.service;

import jie.flyer.rocketmq.AdapterMessage;

/**
 * @author kain
 * @since 2019-11-28
 */
public interface IRocketmqService {

    /**
     * 发送消息到rocketmq,默认超时时间3秒
     *
     * @param message the message
     * @return the boolean
     */
    boolean send(AdapterMessage message);

    /**
     * 发送消息到rocketmq
     *
     * @param message the message
     * @param timeout the timeout
     * @return the boolean
     */
    boolean send(AdapterMessage message, long timeout);
}
