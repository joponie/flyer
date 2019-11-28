package com.github.joponie.flyer.rocketmq.listener;

import com.github.joponie.flyer.rocketmq.constants.MQConstants;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.lang.annotation.*;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RocketListener {
    /**
     * 要消费的topic
     *
     * @return the string
     */
    String topic();

    /**
     * 过滤的表达式,*代表订阅所有tag,多个tag使用||连接
     *
     * @return the string
     */
    String expression() default MQConstants.ALL;

    /**
     * 标识一类Consumer的集合名称,这类Consumer通常消费一类消息,且消费逻辑一致
     *
     * @return the string
     */
    String consumerGroup();

    /**
     * 是否进行消息去重
     *
     * @return the boolean
     */
    boolean repeatFilter() default true;

    /**
     * 消费超时时间
     *
     * @return int int
     */
    int consumeTimeout() default MQConstants.UNINITIALIZED;

    /**
     * 最大重试次数,默认为不重试
     *
     * @return the int
     */
    int maxReconsumeTimes() default MQConstants.UNINITIALIZED;

    /**
     * 最大线程数
     *
     * @return the int
     */
    int consumeMaxThread() default MQConstants.UNINITIALIZED;

    /**
     * 消费模式,默认为集群模式
     *
     * @return message model
     */
    MessageModel consumeMessageModel() default MessageModel.CLUSTERING;

    /**
     * 新的消费者从哪里开始消费
     *
     * @return consume from where
     */
    ConsumeFromWhere consumeFromWhere() default ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;

    /**
     * 消费者第一次消费的时间点
     *
     * @return the string
     */
    String consumeTimestamp() default "";
}