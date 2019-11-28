package com.github.joponie.flyer.portal.domain.impl;

import com.github.joponie.flyer.portal.domain.IMessageService;
import com.github.joponie.flyer.rocketmq.listener.RocketListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 刘杰鹏
 * @since 2019-11-28
 */
@Slf4j
@Service
public class MessageServiceImpl implements IMessageService {

    @RocketListener(topic = "flytest", consumerGroup = "member-group-award-processing")
    public void sendAwardMessage(String jsonMessage) {
        log.info("mq message:{}", jsonMessage);
    }

}
