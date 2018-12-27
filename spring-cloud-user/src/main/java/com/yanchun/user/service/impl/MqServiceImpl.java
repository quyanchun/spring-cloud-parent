package com.yanchun.user.service.impl;

import com.yanchun.user.service.MqService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author quyanchun
 * @Date 2018/12/25
 */
@RestController
public class MqServiceImpl implements MqService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sendMqMessage(String mqQueue, String jsonMessage) {
        rabbitTemplate.convertAndSend(mqQueue,jsonMessage);
    }
}
