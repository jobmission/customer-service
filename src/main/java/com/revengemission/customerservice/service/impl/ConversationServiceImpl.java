package com.revengemission.customerservice.service.impl;

import com.revengemission.customerservice.domain.Conversation;
import com.revengemission.customerservice.domain.ResponseResult;
import com.revengemission.customerservice.persistence.entity.ConversationEntity;
import com.revengemission.customerservice.persistence.entity.ConversationEntityExample;
import com.revengemission.customerservice.persistence.mapper.ConversationEntityMapper;
import com.revengemission.customerservice.service.ConversationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl extends BaseServiceImpl implements ConversationService {

    @Autowired
    ConversationEntityMapper conversationEntityMapper;

    @Override
    public List<Conversation> findByRecipientIdAndStatusGreaterThanEqual(long recipientId, int status) {
        ConversationEntityExample entityExample = new ConversationEntityExample();
        entityExample.createCriteria().andRecipientIdEqualTo(recipientId).andStatusEqualTo(status);
        List<ConversationEntity> conversationEntityList = conversationEntityMapper.selectByExample(entityExample);
        if (conversationEntityList != null) {
            return mapperListObjects(conversationEntityList, Conversation.class);
        } else {
            return null;
        }
    }

    @Override
    public Conversation create(Conversation conversation) {
        ConversationEntity conversationEntity = dozerMapper.map(conversation, ConversationEntity.class);
        conversationEntityMapper.insert(conversationEntity);
        return dozerMapper.map(conversationEntity, Conversation.class);
    }

    @Override
    public Conversation retrieveById(long id) {
        ConversationEntity conversationEntity = conversationEntityMapper.selectByPrimaryKey(id);
        if (conversationEntity != null) {
            return dozerMapper.map(conversationEntity, Conversation.class);
        } else {
            return null;
        }
    }

    @Override
    public ResponseResult updateById(Conversation conversation) {
        ConversationEntity conversationEntity = conversationEntityMapper.selectByPrimaryKey(Long.parseLong(conversation.getId()));
        if (conversationEntity != null) {
            if (StringUtils.isNotEmpty(conversation.getUsername())) {
                conversationEntity.setUsername(conversation.getUsername());
            }
            conversationEntity.setStatus(conversation.getStatus());
            conversationEntityMapper.updateByPrimaryKey(conversationEntity);
        }
        return new ResponseResult();
    }
}
