package com.github.joponie.flyer.rocketmq;

import com.github.joponie.flyer.rocketmq.constants.MQConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
@NoArgsConstructor
@Getter
@Setter
public class RocketListenerInfo {

    /**
     * 要消费的topic
     */
    private String topic;

    /**
     * 过滤的表达式,*代表订阅所有tag,多个tag使用||连接
     */
    private String expression = MQConstants.ALL;

    /**
     * 标识一类Consumer的集合名称,这类Consumer通常消费一类消息,且消费逻辑一致
     */
    private String consumerGroup;

    /**
     * 是否进行消息去重
     */
    private boolean repeatFilter = true;

    /**
     * 消费超时时间
     */
    private int consumeTimeout = MQConstants.UNINITIALIZED;

    /**
     * 最大重试次数
     */
    private int maxReconsumeTimes = MQConstants.UNINITIALIZED;

    /**
     * 最大线程数
     */
    private int consumeMaxThread = MQConstants.UNINITIALIZED;

    /**
     * 新的消费者从哪里开始消费
     */
    private ConsumeFromWhere consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;

    /**
     * 消费者第一次消费的时间点
     */
    private String consumeTimestamp = "";

    /**
     * 消费模式,默认为集群模式
     */
    private MessageModel consumeMessageModel = MessageModel.CLUSTERING;


    public static RocketListenerInfo consume(String topic, String consumerGroup) {
        RocketListenerInfo rocketListenerInfo = new RocketListenerInfo();
        rocketListenerInfo.topic = topic;
        rocketListenerInfo.consumerGroup = consumerGroup;
        return rocketListenerInfo;
    }

    public RocketListenerInfo expression(String expression) {
        this.expression = expression;
        return this;
    }

    public RocketListenerInfo repeatFilter(boolean repeatFilter) {
        this.repeatFilter = repeatFilter;
        return this;
    }

    public RocketListenerInfo consumeTimeout(int consumeTimeout) {
        this.consumeTimeout = consumeTimeout;
        return this;
    }

    public RocketListenerInfo maxReconsumeTimes(int maxReconsumeTimes) {
        this.maxReconsumeTimes = maxReconsumeTimes;
        return this;
    }

    public RocketListenerInfo consumeMaxThread(int consumeMaxThread) {
        this.consumeMaxThread = consumeMaxThread;
        return this;
    }

    public RocketListenerInfo consumeFromWhere(ConsumeFromWhere consumeFromWhere) {
        this.consumeFromWhere = consumeFromWhere;
        return this;
    }

    public RocketListenerInfo consumeTimestamp(String consumeTimestamp) {
        this.consumeTimestamp = consumeTimestamp;
        return this;
    }

    public RocketListenerInfo consumeMessageModel(MessageModel consumeMessageModel) {
        this.consumeMessageModel = consumeMessageModel;
        return this;
    }
}
