package com.github.joponie.flyer.rocketmq.listener;

import com.github.joponie.flyer.rocketmq.RocketListenerInfo;
import com.github.joponie.flyer.rocketmq.container.AbstractConsumerListenerContainer;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 处理RocketListener注解
 *
 * @author kain
 * @since 2019-11-28
 */
@Order
public class RocketListenerAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Resource
    private AbstractConsumerListenerContainer consumerListenerContainer;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        Set<RocketListener> emptySet = new HashSet<>();
        ReflectionUtils
                .doWithMethods(targetClass,
                        method ->
                                Optional.ofNullable(AnnotationUtils.findAnnotation(method, RocketListener.class))
                                        .map(annos -> Stream.of(annos).collect(Collectors.toSet()))
                                        .orElse(emptySet)
                                        .forEach(rocketListener -> {
                                            Class<?>[] types = method.getParameterTypes();
                                            if (types.length != 1) {
                                                throw new RuntimeException("mq handle param must only one");
                                            }
                                            consumerListenerContainer.createConsumer(getRocketListenerInfo(rocketListener), t -> method.invoke(bean, t), types[0]);
                                        }),
                        ReflectionUtils.USER_DECLARED_METHODS);
        return bean;
    }

    private RocketListenerInfo getRocketListenerInfo(RocketListener rocketListener) {
        return RocketListenerInfo.consume(rocketListener.topic(), rocketListener.consumerGroup())
                .expression(rocketListener.expression())
                .repeatFilter(rocketListener.repeatFilter())
                .consumeTimeout(rocketListener.consumeTimeout())
                .consumeMaxThread(rocketListener.consumeMaxThread())
                .maxReconsumeTimes(rocketListener.maxReconsumeTimes())
                .consumeFromWhere(rocketListener.consumeFromWhere())
                .consumeTimestamp(rocketListener.consumeTimestamp())
                .consumeMessageModel(rocketListener.consumeMessageModel());
    }
}
