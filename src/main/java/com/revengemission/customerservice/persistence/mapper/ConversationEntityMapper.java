package com.revengemission.customerservice.persistence.mapper;

import com.revengemission.customerservice.persistence.entity.ConversationEntity;
import com.revengemission.customerservice.persistence.entity.ConversationEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConversationEntityMapper {
    long countByExample(ConversationEntityExample example);

    int deleteByExample(ConversationEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ConversationEntity record);

    int insertSelective(ConversationEntity record);

    List<ConversationEntity> selectByExample(ConversationEntityExample example);

    ConversationEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConversationEntity record, @Param("example") ConversationEntityExample example);

    int updateByExample(@Param("record") ConversationEntity record, @Param("example") ConversationEntityExample example);

    int updateByPrimaryKeySelective(ConversationEntity record);

    int updateByPrimaryKey(ConversationEntity record);
}