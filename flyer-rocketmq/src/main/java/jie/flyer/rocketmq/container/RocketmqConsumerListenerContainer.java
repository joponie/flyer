package jie.flyer.rocketmq.container;

import com.alibaba.fastjson.JSON;
import jie.flyer.rocketmq.Handler;
import jie.flyer.rocketmq.RocketListenerInfo;
import jie.flyer.rocketmq.util.MQUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kain
 * @since 2019-11-28
 */
public class RocketmqConsumerListenerContainer extends AbstractConsumerListenerContainer {

    private static final Set<DefaultMQPushConsumer> consumers = new HashSet<>();

    /**
     * 创建消费者
     *
     * @param rocketListenerInfo the rocket listener info
     * @param handler            the handler
     * @param paramType          the param type
     */
    @Override
    <T> void doCreateConsumer(RocketListenerInfo rocketListenerInfo, Handler<T> handler, Class<T> paramType) {
        boolean isRepeat = isRepeat(rocketListenerInfo);
        String topic = MQUtils.wrapProfile(rocketListenerInfo.getTopic());
        String group = MQUtils.wrapProfile(rocketListenerInfo.getConsumerGroup());
        boolean reconsume = rocketListenerInfo.getMaxReconsumeTimes() > 0;
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQUtils.wrapProfile(rocketListenerInfo.getConsumerGroup()));
        consumer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        consumer.setConsumeFromWhere(rocketListenerInfo.getConsumeFromWhere());
        consumer.setConsumerGroup(group);
        consumer.setMessageModel(rocketListenerInfo.getConsumeMessageModel());
        if (StringUtils.hasLength(rocketListenerInfo.getConsumeTimestamp())) {
            consumer.setConsumeTimestamp(rocketListenerInfo.getConsumeTimestamp());
        }
        if (reconsume) {
            consumer.setMaxReconsumeTimes(rocketListenerInfo.getMaxReconsumeTimes());
        } else {
            consumer.setMaxReconsumeTimes(0);
        }
        if (rocketListenerInfo.getConsumeTimeout() > 0) {
            consumer.setConsumeTimeout(rocketListenerInfo.getConsumeTimeout());
        }
        if (rocketListenerInfo.getConsumeMaxThread() > 0) {
            consumer.setConsumeThreadMax(rocketListenerInfo.getConsumeMaxThread());
        }
        try {
            consumer.subscribe(topic, rocketListenerInfo.getExpression());
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                boolean success = true;
                for (MessageExt messageExt : msgs) {
                    logger.info("consume mq message topic:{},tag:{}, key:{}", topic, messageExt.getTags(), messageExt.getKeys());
                    if (isRepeat && repeatFilter.filterRepeat(group, messageExt.getKeys())) {
                        logger.info("consume mq message repeat, key:{}, body:{}.", messageExt.getKeys(), new String(messageExt.getBody()));
                        continue;
                    }
                    try {
                        handler.handle(JSON.parseObject(messageExt.getBody(), paramType));
                    } catch (Exception e) {
                        logger.error("rocketmq handle error, key:{}, body:{}, messages:{}",
                                messageExt.getKeys(), new String(messageExt.getBody()), messageExt, e);
                        if (isRepeat) {
                            repeatFilter.resetRepeat(group, messageExt.getKeys());
                        }
                        success = false;
                    }
                }
                return success || !reconsume ? ConsumeConcurrentlyStatus.CONSUME_SUCCESS : ConsumeConcurrentlyStatus.RECONSUME_LATER;
            });
            consumers.add(consumer);
            if (isStarted) {
                //容器已启动,消费者才被加进来,因此加进来的都需要直接启动
                startConsumer(consumer);
            }
        } catch (MQClientException e) {
            logger.error("processRocketListener error,group:{},topic:{}", group, topic, e);
        }
    }

    @Override
    void doStartAllConsumer() {
        try {
            for (DefaultMQPushConsumer consumer : consumers) {
                startConsumer(consumer);
            }
        } catch (Exception e) {
            logger.error("rocketmq consumer start fail", e);
        }
    }


    /**
     * 关闭容器
     */
    @Override
    void doShutdownAllConsumer() {
        consumers.forEach(DefaultMQPushConsumer::shutdown);
    }

    /**
     * 启动单个消费者
     */
    private void startConsumer(DefaultMQPushConsumer consumer) throws MQClientException {
        consumer.start();
        logger.info("rocketmq consumer start. consumerGroup: {}", consumer.getConsumerGroup());
    }
}
