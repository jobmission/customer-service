package com.revengemission.customerservice.persistence.mapper;

import com.revengemission.customerservice.persistence.entity.ConversationMessageEntity;
import com.revengemission.customerservice.persistence.entity.ConversationMessageEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConversationMessageEntityMapper {
    long countByExample(ConversationMessageEntityExample example);

    int deleteByExample(ConversationMessageEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ConversationMessageEntity record);

    int insertSelective(ConversationMessageEntity record);

    List<ConversationMessageEntity> selectByExample(ConversationMessageEntityExample example);

    ConversationMessageEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConversationMessageEntity record, @Param("example") ConversationMessageEntityExample example);

    int updateByExample(@Param("record") ConversationMessageEntity record, @Param("example") ConversationMessageEntityExample example);

    int updateByPrimaryKeySelective(ConversationMessageEntity record);

    int updateByPrimaryKey(ConversationMessageEntity record);
}