package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConversationMessage extends BaseDomain {
    private Long conversationId;
    private String message;
    private String author;
    private boolean customer;
    private Date date;
}
