package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Discussion extends BaseDomain {
    private Long userId;
    private Long discussionTopicId;
    private String author;
    private String title;
    private String content;
    private String tags;
    private int viewCount;
    private int commentCount;
}
