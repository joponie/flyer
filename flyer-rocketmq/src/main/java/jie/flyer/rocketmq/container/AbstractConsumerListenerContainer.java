package jie.flyer.rocketmq.container;

import jie.flyer.rocketmq.Handler;
import jie.flyer.rocketmq.RocketListenerInfo;
import jie.flyer.rocketmq.RocketmqProperties;
import jie.flyer.rocketmq.filter.RepeatFilter;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kain
 * @since 2019-11-28
 */
public abstract class AbstractConsumerListenerContainer implements CommandLineRunner {

    private static final Object MUTEX = new Object();
    /**
     * 容器是否已经启动
     */
    protected static transient boolean isStarted = false;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Lazy
    @Resource
    protected RocketmqProperties rocketmqProperties;
    @Lazy
    @Autowired(required = false)
    protected RepeatFilter repeatFilter;
    private Set<String> consumerGroup = new HashSet<>();

    @Override
    public void run(String... args) throws Exception {
        startAllConsumer();
    }

    public final <T> void createConsumer(RocketListenerInfo rocketListenerInfo, Handler<T> handler, Class<T> paramType) {
        if (rocketmqProperties.getConsumeEnabled()) {
            if (rocketmqProperties.getConsumeTopic().isEmpty() || rocketmqProperties.getConsumeTopic().contains(rocketListenerInfo.getTopic())) {
                synchronized (MUTEX) {
                    if (consumerGroup.add(rocketListenerInfo.getConsumerGroup())) {
                        doCreateConsumer(rocketListenerInfo, handler, paramType);
                    }
                }
            }
        }
    }

    public final void shutdownAllConsumer() {
        if (rocketmqProperties.getConsumeEnabled()) {
            synchronized (MUTEX) {
                logger.info("-----------   shutdown all  consumer start  ------------------");
                doShutdownAllConsumer();
                isStarted = false;
                logger.info("----------- shutdown all ons consumer finish------------------");
            }
        }
    }

    public final void startAllConsumer() {
        if (rocketmqProperties.getConsumeEnabled()) {
            synchronized (MUTEX) {
                if (!isStarted) {
                    logger.info("-----------------     start container   -----------------------");
                    doStartAllConsumer();
                    isStarted = true;
                    logger.info("----------------- start container finish-----------------------");
                }
            }
        }
    }

    /**
     * 创建消费者
     *
     * @param <T>                the type parameter
     * @param rocketListenerInfo the rocket listener info
     * @param handler            the handler
     * @param paramType          the param type
     */
    abstract <T> void doCreateConsumer(RocketListenerInfo rocketListenerInfo, Handler<T> handler, Class<T> paramType);

    /**
     * 启动容器
     *
     * @throws Exception the exception
     */
    abstract void doStartAllConsumer();


    /**
     * 关闭容器
     */
    abstract void doShutdownAllConsumer();

    /**
     * 是否需要去重
     *
     * @param rocketListenerInfo the rocket listener info
     * @return the boolean
     */
    protected final boolean isRepeat(RocketListenerInfo rocketListenerInfo) {
        //如果是广播机制,则不需要去重
        boolean isRepeat = rocketListenerInfo.getConsumeMessageModel() != MessageModel.BROADCASTING && rocketListenerInfo.isRepeatFilter();
        if (isRepeat && repeatFilter == null) {
            logger.error("rocketListener use repeat,but not find repeatFilter,topic:{},consumer:{}", rocketListenerInfo.getTopic(), rocketListenerInfo.getConsumerGroup());
            throw new RuntimeException("rocketListener use repeat,but not find repeatFilter");
        }
        return isRepeat;
    }
}
