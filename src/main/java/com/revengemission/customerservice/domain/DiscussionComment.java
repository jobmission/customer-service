package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscussionComment extends BaseDomain {
    private Long userId;
    private Long discussionId;
    private String author;
    private String content;
}
