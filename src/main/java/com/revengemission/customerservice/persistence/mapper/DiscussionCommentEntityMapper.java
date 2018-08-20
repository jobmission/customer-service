package com.revengemission.customerservice.persistence.mapper;

import com.revengemission.customerservice.persistence.entity.DiscussionCommentEntity;
import com.revengemission.customerservice.persistence.entity.DiscussionCommentEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscussionCommentEntityMapper {
    long countByExample(DiscussionCommentEntityExample example);

    int deleteByExample(DiscussionCommentEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DiscussionCommentEntity record);

    int insertSelective(DiscussionCommentEntity record);

    List<DiscussionCommentEntity> selectByExample(DiscussionCommentEntityExample example);

    DiscussionCommentEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DiscussionCommentEntity record, @Param("example") DiscussionCommentEntityExample example);

    int updateByExample(@Param("record") DiscussionCommentEntity record, @Param("example") DiscussionCommentEntityExample example);

    int updateByPrimaryKeySelective(DiscussionCommentEntity record);

    int updateByPrimaryKey(DiscussionCommentEntity record);
}