package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Conversation extends BaseDomain {
    private int status;
    private String initiatorId;
    private String username;
    private String recipientId;

    private String ip;
    private String os;
    private String browser;
}
