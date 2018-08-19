package com.revengemission.customerservice.persistence.mapper;

import com.revengemission.customerservice.persistence.entity.DiscussionTopicEntity;
import com.revengemission.customerservice.persistence.entity.DiscussionTopicEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscussionTopicEntityMapper {
    long countByExample(DiscussionTopicEntityExample example);

    int deleteByExample(DiscussionTopicEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DiscussionTopicEntity record);

    int insertSelective(DiscussionTopicEntity record);

    List<DiscussionTopicEntity> selectByExample(DiscussionTopicEntityExample example);

    DiscussionTopicEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DiscussionTopicEntity record, @Param("example") DiscussionTopicEntityExample example);

    int updateByExample(@Param("record") DiscussionTopicEntity record, @Param("example") DiscussionTopicEntityExample example);

    int updateByPrimaryKeySelective(DiscussionTopicEntity record);

    int updateByPrimaryKey(DiscussionTopicEntity record);
}