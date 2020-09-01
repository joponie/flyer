package jie.flyer.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.rocketmq.common.message.Message;

import java.util.Optional;

/**
 * The type Adapter message.
 *
 * @author kain
 * @since 2019-11-28
 */
@AllArgsConstructor
@ToString
public class AdapterMessage {

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
     * 延时等级
     * 需要在broker配置,rocketmq默认的配置如下
     * messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * 0表示不延时,1表示1s,2表示5s以此类推
     */
    private Integer delayTimeLevel;

    /**
     * 具体的延时时间,ons可以指定具体的延时时间,不过最多7天,单位:毫秒
     */
    private Long delayTime;

    /**
     * 获取rocket Message
     *
     * @return the rocket mq message
     */
    public Message getRocketMQMessage() {
        Message message = new Message(this.topic, this.tags, this.keys, JSON.toJSONBytes(this.body));
        Optional.ofNullable(this.delayTimeLevel).ifPresent(message::setDelayTimeLevel);
        return message;
    }
}
