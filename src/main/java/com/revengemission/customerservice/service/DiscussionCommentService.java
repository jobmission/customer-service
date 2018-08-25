package com.revengemission.customerservice.service;

import com.revengemission.customerservice.domain.DiscussionComment;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.NotImplementException;

public interface DiscussionCommentService extends CommonInterface<DiscussionComment> {

    default JsonObjects<DiscussionComment> listDiscussionComment(long discussionId, int pageNum, int pageSize) {
        throw new NotImplementException();
    }
}
