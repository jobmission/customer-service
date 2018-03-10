package com.revengemission.customerservice.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class ConversationEntity extends BaseEntity {

    private int status;//0未开始，1已开始，-1已关闭
    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '会话发起人id'")
    private Long initiatorId;
    private String username;
    @Column(columnDefinition = "BIGINT(20) COMMENT '客服id'")
    private Long recipientId;
    private String ip;
    private String os;
    private String browser;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Long initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

}
