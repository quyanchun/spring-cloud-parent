package com.yanchun.common.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
@Data
public class Sms implements Serializable {
    private Long id;
    private String phone;
    private String signName;
    private String templateCode;
    private String params;
    private String bizId;
    private String code;
    private String message;
    private String content;
    private Date day;
    private Date createTime;
    private Date updateTime;
}
