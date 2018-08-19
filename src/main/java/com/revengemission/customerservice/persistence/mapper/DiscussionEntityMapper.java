package com.revengemission.customerservice.persistence.mapper;

import com.revengemission.customerservice.persistence.entity.DiscussionEntity;
import com.revengemission.customerservice.persistence.entity.DiscussionEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscussionEntityMapper {
    long countByExample(DiscussionEntityExample example);

    int deleteByExample(DiscussionEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DiscussionEntity record);

    int insertSelective(DiscussionEntity record);

    List<DiscussionEntity> selectByExample(DiscussionEntityExample example);

    DiscussionEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DiscussionEntity record, @Param("example") DiscussionEntityExample example);

    int updateByExample(@Param("record") DiscussionEntity record, @Param("example") DiscussionEntityExample example);

    int updateByPrimaryKeySelective(DiscussionEntity record);

    int updateByPrimaryKey(DiscussionEntity record);
}