package jie.flyer.rocketmq;

import jie.flyer.rocketmq.constants.MQConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kain
 * @since 2019-11-28
 */
@ConfigurationProperties(prefix = "rocketmq")
@Getter
@Setter
public class RocketmqProperties implements InitializingBean {

    /**
     * 启用
     */
    private Boolean enabled;

    /**
     * 生产者组别
     */
    private String producerGroup;

    /**
     * redis去重的key,如果使用redis去重,则需要设置这个值
     */
    private String redisRepeatKey;

    /**
     * rocketmq指定环境
     */
    private String profiles;

    /**
     * spring profile
     */
    @Value("${spring.profiles.active:}")
    private String springProfiles;

    /**
     * 是否自动创建topic
     */
    private Boolean autoCreateTopic = true;

    /**
     * namesrv的地址多个地址使用;分隔
     */
    private String namesrvAddr;

    /**
     * 是否允许消费
     */
    private Boolean consumeEnabled = true;

    /**
     * 指定能消费的topic
     */
    private List<String> consumeTopic = new ArrayList<>();

    public static RocketmqProperties accessProperties() {
        return PropertiesHolder.rocketmqProperties;
    }

    public String getPrefixProfiles() {
        if (StringUtils.hasLength(profiles)) {
            return profiles + MQConstants.DASH_SEPARATOR;
        } else if (StringUtils.hasLength(springProfiles)) {
            return springProfiles + MQConstants.DASH_SEPARATOR;
        }
        return "";
    }

    @Override
    public void afterPropertiesSet() {
        PropertiesHolder.rocketmqProperties = this;
    }

    private static class PropertiesHolder {
        private static RocketmqProperties rocketmqProperties;
    }

}
