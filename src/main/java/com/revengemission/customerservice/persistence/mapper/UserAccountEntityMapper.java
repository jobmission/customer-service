package com.revengemission.customerservice.persistence.mapper;

import com.revengemission.customerservice.persistence.entity.UserAccountEntity;
import com.revengemission.customerservice.persistence.entity.UserAccountEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountEntityMapper {
    long countByExample(UserAccountEntityExample example);

    int deleteByExample(UserAccountEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAccountEntity record);

    int insertSelective(UserAccountEntity record);

    List<UserAccountEntity> selectByExample(UserAccountEntityExample example);

    UserAccountEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAccountEntity record, @Param("example") UserAccountEntityExample example);

    int updateByExample(@Param("record") UserAccountEntity record, @Param("example") UserAccountEntityExample example);

    int updateByPrimaryKeySelective(UserAccountEntity record);

    int updateByPrimaryKey(UserAccountEntity record);
}