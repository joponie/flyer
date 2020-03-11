package com.github.joponie.flyer.pay.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.joponie.flyer.portal.dal.model.PointRecord;
import com.github.joponie.flyer.rocketmq.RocketmqProperties;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘杰鹏
 * @since 2020-03-03
 */
@Component
@Slf4j
public class TransactionProducer implements InitializingBean {

    public static final String TOPIC = "t_massage";
    private TransactionMQProducer producer;

    @Autowired
    private RocketmqProperties rocketMQProperties;

    @Autowired
    private TransactionListener transactionListener;


    /**
     * 构造生产者
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        producer = new TransactionMQProducer(rocketMQProperties.getProducerGroup());
        producer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("transaction-thread-name-%s").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(30), threadFactory);
        producer.setExecutorService(executor);

        producer.setTransactionListener(transactionListener);

        producer.start();

    }

    /**
     * 真正的事物消息发送者
     */
    public void send() throws JsonProcessingException, UnsupportedEncodingException, MQClientException {

        ObjectMapper objectMapper = new ObjectMapper();

        // 模拟接受前台的支付请求
        String orderNo = UUID.randomUUID().toString();
        Integer userId = 1;
        // 构造发送的事务 消息
        PointRecord record = new PointRecord();
        record.setUserId(userId);
        record.setOrderNo(orderNo);

        Message message = new Message(TOPIC, "", record.getOrderNo(),
                objectMapper.writeValueAsString(record).getBytes(RemotingHelper.DEFAULT_CHARSET));

        producer.sendMessageInTransaction(message, null);

        log.info("发送事务消息， topic = {}, body = {}", TOPIC, record);
    }
}

