package com.revengemission.customerservice.dao.repertory;

import com.revengemission.customerservice.dao.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
    List<ConversationEntity> findByRecipientIdAndStatusGreaterThanEqual(long recipientId, int status);
}
