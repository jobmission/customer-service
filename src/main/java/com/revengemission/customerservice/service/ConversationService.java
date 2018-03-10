package com.revengemission.customerservice.service;

import com.revengemission.customerservice.domain.Conversation;
import com.revengemission.customerservice.domain.NotImplementException;

import java.util.List;

public interface ConversationService extends CommonInterface<Conversation> {
    default List<Conversation> findByRecipientIdAndStatusGreaterThanEqual(long recipientId, int status) {
        throw new NotImplementException();
    }
}
