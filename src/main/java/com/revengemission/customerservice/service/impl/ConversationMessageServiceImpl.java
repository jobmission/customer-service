package com.revengemission.customerservice.service.impl;

import com.revengemission.customerservice.domain.ConversationMessage;
import com.revengemission.customerservice.persistence.entity.ConversationMessageEntity;
import com.revengemission.customerservice.persistence.mapper.ConversationMessageEntityMapper;
import com.revengemission.customerservice.service.ConversationMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConversationMessageServiceImpl extends BaseServiceImpl implements ConversationMessageService {

    @Autowired
    ConversationMessageEntityMapper conversationMessageEntityMapper;


    @Override
    @Transactional
    public ConversationMessage create(ConversationMessage conversationMessage) {
        ConversationMessageEntity conversationMessageEntity = dozerMapper.map(conversationMessage, ConversationMessageEntity.class);
        conversationMessageEntityMapper.insert(conversationMessageEntity);
        return dozerMapper.map(conversationMessageEntity, ConversationMessage.class);
    }
}
