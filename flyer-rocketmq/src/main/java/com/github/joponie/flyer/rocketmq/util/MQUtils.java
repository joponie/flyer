package com.github.joponie.flyer.rocketmq.util;

import com.github.joponie.flyer.rocketmq.RocketmqProperties;
import com.github.joponie.flyer.rocketmq.constants.MQConstants;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
public abstract class MQUtils {


    /**
     * 使用profile来包装
     *
     * @param item the item
     * @return the string
     */
    public static String wrapProfile(String item) {
        return RocketmqProperties.accessProperties().getPrefixProfiles() + item;
    }


    /**
     * 使用profile来包装consumerId
     *
     * @param consumerName the consumer name
     * @return the string
     */
    public static String wrapConsumerId(String consumerName) {
        return MQConstants.ONS_CONSUMER_ID_PREFIX + wrapProfile(consumerName);
    }
}
