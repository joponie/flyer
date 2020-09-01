package jie.flyer.pay.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import jie.flyer.portal.dal.model.PointRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author kain
 * @since 2020-03-03
 */
@Component
@Slf4j
public class PointTransactionListener implements TransactionListener {

    @Autowired
    private PayService payService;

    /**
     * 根据消息发送的结果 判断是否执行本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        // 根据本地事务执行成与否判断 事务消息是否需要commit与 rollback
        ObjectMapper objectMapper = new ObjectMapper();
        LocalTransactionState state = LocalTransactionState.UNKNOW;
        try {
            PointRecord record = objectMapper.readValue(message.getBody(), PointRecord.class);
            payService.pay(record.getOrderNo(), record.getUserId());
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        } catch (UnsupportedEncodingException e) {
            log.error("反序列化消息 不支持的字符编码：{}", e);
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        } catch (IOException e) {
            log.error("反序列化消息失败 io异常：{}", e);
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }

    /**
     * RocketMQ 回调 根据本地事务是否执行成功 告诉broker 此消息是否投递成功
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        ObjectMapper objectMapper = new ObjectMapper();
        LocalTransactionState state = LocalTransactionState.UNKNOW;
        PointRecord record = null;
        try {
            record = objectMapper.readValue(messageExt.getBody(), PointRecord.class);
        } catch (IOException e) {
            log.error("回调检查本地事务状态异常： ={}", e);

        }
        try {
            //根据是否有transaction_id对应转账记录 来判断事务是否执行成功
            boolean isCommit = payService.checkPayStatus(record.getOrderNo());
            if (isCommit) {
                state = LocalTransactionState.COMMIT_MESSAGE;
            } else {
                state = LocalTransactionState.ROLLBACK_MESSAGE;
            }
        } catch (Exception e) {
            log.error("回调检查本地事务状态异常： ={}", e);
        }
        return state;

    }
}
