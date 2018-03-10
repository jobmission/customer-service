package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConversationMessage {
    private Long conversationId;
    private String message;
    private String messageType;
    private String messageFrom;
    private String messageTo;
    private String username;
    private Date date;
}
