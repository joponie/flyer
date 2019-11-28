package com.github.joponie.flyer.rocketmq.service;

import com.github.joponie.flyer.rocketmq.AdapterMessage;
import com.github.joponie.flyer.rocketmq.RocketmqProperties;
import com.github.joponie.flyer.rocketmq.constants.MQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
@Slf4j
public class RocketmqServiceImpl implements IRocketmqService {

    private static final int QUEUE_NUMBER = 4;
    /**
     * rocketmq第一次比较慢
     */
    private static final int TIMEOUT = 15000;
    private static final Set<String> topics = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
    private static transient boolean firstSend = true;

    @Resource
    private DefaultMQProducer producer;

    @Resource
    private RocketmqProperties rocketmqProperties;

    /**
     * 发送消息到rocketmq
     *
     * @param message the message
     */
    @Override
    public boolean send(AdapterMessage message) {
        return send(message, MQConstants.DEFAULT_TIMEOUT);
    }

    /**
     * 发送消息到rocketmq
     *
     * @param message the message
     * @param timeout the timeout
     */
    @Override
    public boolean send(AdapterMessage message, long timeout) {
        if (firstSend) {
            timeout = TIMEOUT;
            firstSend = false;
        }
        Message rocketMessage = message.getRocketMQMessage();
        try {
            if (rocketmqProperties.getAutoCreateTopic() && topics.add(rocketMessage.getTopic())) {
                //创建topic
                log.info("create Topic:{}", rocketMessage.getTopic());
                producer.createTopic(producer.getCreateTopicKey(), rocketMessage.getTopic(), QUEUE_NUMBER);
            }
            log.info("send rocketmq message topic:{},tag:{}, key:{}", rocketMessage.getTopic(), rocketMessage.getTags(), rocketMessage.getKeys());
            SendResult sendResult = producer.send(rocketMessage, timeout);
            if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                return true;
            }
            log.error("send rocketmq message error message:{}, key:{}, sendResult:{}, sendBody:{}.",
                    message, rocketMessage.getKeys(), sendResult, new String(rocketMessage.getBody()));
            return false;
        } catch (Exception e) {
            log.error("send rocketmq message error key:{}, sendBody:{}, message:{}.",
                    rocketMessage.getKeys(), new String(rocketMessage.getBody()), message, e);
            return false;
        }
    }
}
