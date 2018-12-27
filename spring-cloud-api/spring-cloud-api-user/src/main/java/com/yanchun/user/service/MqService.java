package com.yanchun.user.service;

/**
 * @Author quyanchun
 * @Date 2018/12/25
 */
public interface MqService {

    void sendMqMessage(String mqQueue,String jsonMessage);
}
