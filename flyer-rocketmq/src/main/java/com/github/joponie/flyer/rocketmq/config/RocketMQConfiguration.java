package com.github.joponie.flyer.rocketmq.config;

import com.github.joponie.flyer.rocketmq.RocketmqProperties;
import com.github.joponie.flyer.rocketmq.container.RocketmqConsumerListenerContainer;
import com.github.joponie.flyer.rocketmq.filter.RedisRepeatFilter;
import com.github.joponie.flyer.rocketmq.filter.RepeatFilter;
import com.github.joponie.flyer.rocketmq.listener.RocketListenerAnnotationBeanPostProcessor;
import com.github.joponie.flyer.rocketmq.service.IRocketmqService;
import com.github.joponie.flyer.rocketmq.service.RocketmqServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
@Getter
@Setter
@ConditionalOnProperty(prefix = "rocketmq", value = "enabled", havingValue = "true")
@EnableConfigurationProperties(RocketmqProperties.class)
public class RocketMQConfiguration {

    /**
     * redis去重
     */
    @ConditionalOnMissingBean(RepeatFilter.class)
    @Bean
    public RepeatFilter redisRepeatFilter(RocketmqProperties rocketmqProperties) {
        if (StringUtils.hasLength(rocketmqProperties.getRedisRepeatKey())) {
            return new RedisRepeatFilter();
        }
        return null;
    }


    /**
     * rocketmq发送消费实现
     */
    @Bean
    public IRocketmqService rocketmqService() {
        return new RocketmqServiceImpl();
    }

    /**
     * 消费者容器
     */
    @ConditionalOnProperty(prefix = "rocketmq", value = "consume-enabled", havingValue = "true", matchIfMissing = true)
    @Bean(destroyMethod = "shutdownAllConsumer")
    public RocketmqConsumerListenerContainer consumerListenerContainer() {
        return new RocketmqConsumerListenerContainer();
    }

    /**
     * RocketListener注解处理
     */
    @ConditionalOnProperty(prefix = "rocketmq", value = "consume-enabled", havingValue = "true", matchIfMissing = true)
    @Bean
    public RocketListenerAnnotationBeanPostProcessor rocketListenerAnnotationBeanPostProcessor() {
        return new RocketListenerAnnotationBeanPostProcessor();
    }

    /**
     * 创建rocketmq生产者
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer rocketMQProducer(RocketmqProperties rocketmqProperties) {
        DefaultMQProducer producer = new DefaultMQProducer(rocketmqProperties.getProducerGroup());
        producer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        return producer;
    }
}
