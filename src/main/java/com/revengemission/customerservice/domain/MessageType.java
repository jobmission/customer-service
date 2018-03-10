package com.revengemission.customerservice.domain;


public enum MessageType {
    TOPIC("主题消息"),
    QUEUE("个人消息"),
    SYSTEM("系统消息");

    private String meaning;

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    MessageType() {
    }

    MessageType(String meaning) {
        this.meaning = meaning;
    }
}
