package com.yanchun.common.model;

/**
 * @Author quyanchun
 * @Date 2018/11/16
 */
public class Mail {
  private String sender;//发件人
  private String recipient;//接受人
  private String title;//主题
  private String content;//内容

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
