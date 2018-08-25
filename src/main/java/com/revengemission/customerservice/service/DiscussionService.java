package com.revengemission.customerservice.service;

import com.revengemission.customerservice.domain.Discussion;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.NotImplementException;

public interface DiscussionService extends CommonInterface<Discussion> {

    default JsonObjects<Discussion> listDiscussion(Long discussionTopicId, int pageNum, int pageSize) {
        throw new NotImplementException();
    }

    default JsonObjects<Discussion> listBestDiscussion(int pageNum, int pageSize) {
        throw new NotImplementException();
    }
}
