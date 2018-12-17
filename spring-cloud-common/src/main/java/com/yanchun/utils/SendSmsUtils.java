package com.yanchun.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.yanchun.config.AliyunSmsConfig;
import com.yanchun.model.Sms;

/**
 * @Author quyanchun
 * @Date 2018/12/17
 */
public class SendSmsUtils {

    /**
     * 异步发送阿里云短信
     *
     * @param sms
     * @return
     */
    public static SendSmsResponse sendSmsMsg(Sms sms) {
        SendSmsResponse sendSmsResponse = null;
        try {
            if (sms.getSignName() == null) {
                sms.setSignName(AliyunSmsConfig.signName);
            }

            if (sms.getTemplateCode() == null) {
                sms.setTemplateCode(AliyunSmsConfig.ShareTemplateCode);
            }

            // 阿里云短信官网demo代码
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(sms.getMobile());
            request.setSignName(sms.getSignName());
            request.setTemplateCode(sms.getTemplateCode());
            request.setTemplateParam(sms.getParams());
            request.setOutId("自定义id");
            sendSmsResponse = AliyunSmsConfig.iAcsClient.getAcsResponse(request);
            if (sendSmsResponse != null) {
                sms.setCode(sendSmsResponse.getCode());
                sms.setMessage(sendSmsResponse.getMessage());
                sms.setBizId(sendSmsResponse.getBizId());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return sendSmsResponse;
    }

}
