package com.yanchun.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 阿里云短信配置
 */
public class AliyunSmsConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AliyunSmsConfig.class);
    private static String accessKeyId = "LTAIUlks5QuShCOw";
    private static String accessKeySecret = "yvvTEerFBnWzvYrQJAlWh1uLKPFeXZ";
    public static String signName="AI圈";
    public static String ShareTemplateCode = "SMS_150485602";//通用验证码
    public static IAcsClient iAcsClient = null;
    static {
        iAcsClient();
    }
    public static void iAcsClient() {
        try {
            // 设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            // 初始化ascClient需要的几个参数
            final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
            final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
            // 初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            iAcsClient = new DefaultAcsClient(profile);
        } catch (Exception e) {
            LOGGER.error("==============阿里云短信服务加载失败：{}",e.getMessage());
        }

    }
}
