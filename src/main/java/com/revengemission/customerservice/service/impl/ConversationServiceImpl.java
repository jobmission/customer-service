package com.revengemission.customerservice.service.impl;

import com.revengemission.customerservice.dao.entity.ConversationEntity;
import com.revengemission.customerservice.dao.repertory.ConversationRepository;
import com.revengemission.customerservice.domain.Conversation;
import com.revengemission.customerservice.domain.ResponseResult;
import com.revengemission.customerservice.service.ConversationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl extends BaseServiceImpl implements ConversationService {

    @Autowired
    ConversationRepository conversationRepository;

    @Override
    public List<Conversation> findByRecipientIdAndStatusGreaterThanEqual(long recipientId, int status) {
        List<ConversationEntity> conversationEntityList = conversationRepository.findByRecipientIdAndStatusGreaterThanEqual(recipientId, status);
        if (conversationEntityList != null) {
            return mapperListObjects(conversationEntityList, Conversation.class);
        } else {
            return null;
        }
    }

    @Override
    public Conversation create(Conversation conversation) {
        ConversationEntity conversationEntity = dozerMapper.map(conversation, ConversationEntity.class);
        conversationEntity = conversationRepository.save(conversationEntity);
        return dozerMapper.map(conversationEntity, Conversation.class);
    }

    @Override
    public Conversation retrieveById(long id) {
        Optional<ConversationEntity> conversationEntityOptional = conversationRepository.findById(id);
        if (conversationEntityOptional.isPresent()) {
            return dozerMapper.map(conversationEntityOptional.get(), Conversation.class);
        } else {
            return null;
        }
    }

    @Override
    public ResponseResult updateById(Conversation conversation) {
        Optional<ConversationEntity> conversationEntityOptional = conversationRepository.findById(Long.parseLong(conversation.getId()));
        conversationEntityOptional.ifPresent(e -> {
            if (StringUtils.isNotEmpty(conversation.getUsername())) {
                e.setUsername(conversation.getUsername());
            }
            e.setStatus(conversation.getStatus());
            conversationRepository.save(e);
        });
        return new ResponseResult();
    }
}
