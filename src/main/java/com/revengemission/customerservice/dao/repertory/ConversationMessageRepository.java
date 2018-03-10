package com.revengemission.customerservice.dao.repertory;

import com.revengemission.customerservice.dao.entity.ConversationMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationMessageRepository extends JpaRepository<ConversationMessageEntity, Long> {
    ConversationMessageEntity findByMessageFrom(String messageFrom);

    ConversationMessageEntity findByMessageTo(String messageTo);

    ConversationMessageEntity findByMessageType(String messageType);

}
