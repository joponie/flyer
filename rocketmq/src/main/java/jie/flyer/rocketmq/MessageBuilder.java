package jie.flyer.rocketmq;

import jie.flyer.rocketmq.util.MQUtils;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kain
 * @since 2019-11-28
 */
public class MessageBuilder {

    /**
     * 标识一类消息的逻辑名字,消息的逻辑管理单位.无论消息生产还是消费,都需要指定Topic
     */
    private String topic;
    /**
     * RocketMQ支持给在发送的时候给topic打tag,同一个topic的消息虽然逻辑管理是一样的.但是消费topic1的时候,如果你订阅的时候指定的是tagA,那么tagB的消息将不会投递
     */
    private String tags;
    /**
     * 自定义key,这里用来进行去重,如果有其它用处的话,需要自行去重,也就是@RocketListener需要把ignoreRepeat置为false
     */
    private String keys;
    private Object body;
    /**
     * 延时,rocketmq不支持具体的延时时间,只支持使用延时等级
     * 需要在broker配置,rocketmq默认的配置如下
     * messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * 0表示不延时,1表示1s,2表示5s以此类推
     */
    private Integer delayTimeLevel;

    /**
     * 具体的延时时间,ons可以指定具体的延时时间,不过最多40天,单位:毫秒
     */
    private Long delayTime;


    private MessageBuilder() {

    }

    private MessageBuilder(String topic) {
        this.topic = topic;
    }

    /**
     * Topic message builder.
     *
     * @param topic the topic
     * @return the message builder
     */
    public static MessageBuilder topic(String topic) {
        Assert.notNull(topic, "topic is null");
        return new MessageBuilder(topic);
    }

    /**
     * Body message builder.
     *
     * @param body the body
     * @return the message builder
     */
    public MessageBuilder body(Object body) {
        Assert.notNull(body, "body is null");
        this.body = body;
        return this;
    }

    /**
     * Tags message builder.
     *
     * @param tags the tags
     * @return the message builder
     */
    public MessageBuilder tags(String tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Keys message builder.
     *
     * @param keys the keys
     * @return the message builder
     */
    public MessageBuilder keys(String keys) {
        this.keys = keys;
        return this;
    }

    /**
     * Delay time level message builder.
     *
     * @param delayTimeLevel the delay time level
     * @return the message builder
     */
    public MessageBuilder delay(int delayTimeLevel, long delayTime) {
        this.delayTimeLevel = delayTimeLevel;
        this.delayTime = delayTime;
        return this;
    }


    /**
     * Build message.
     *
     * @return the message
     */
    public AdapterMessage build() {
        String keys = Optional.ofNullable(this.keys)
                .orElseGet(() -> UUID.randomUUID().toString().replace("-", ""));
        return new AdapterMessage(MQUtils.wrapProfile(topic), tags, keys, body, delayTimeLevel, delayTime);
    }
}
