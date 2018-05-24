package com.revengemission.customerservice.service.impl;

import com.revengemission.customerservice.domain.GlobalConstant;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.ResponseResult;
import com.revengemission.customerservice.domain.UserAccount;
import com.revengemission.customerservice.persistence.entity.UserAccountEntity;
import com.revengemission.customerservice.persistence.entity.UserAccountEntityExample;
import com.revengemission.customerservice.persistence.mapper.UserAccountEntityMapper;
import com.revengemission.customerservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserAccountServiceImpl extends BaseServiceImpl implements UserAccountService {

    @Autowired
    UserAccountEntityMapper userAccountEntityMapper;

    @Override
    public UserAccount login(String username, String password) {

        UserAccountEntityExample example = new UserAccountEntityExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserAccountEntity> entityList = userAccountEntityMapper.selectByExample(example);
        if (entityList != null && entityList.size() > 0) {
            return dozerMapper.map(entityList.get(0), UserAccount.class);
        } else {
            return null;
        }
    }

    @Override
    public JsonObjects<UserAccount> listCommissioners(int pageNum, int pageSize, String sortField, String sortOrder) {
        JsonObjects<UserAccount> jsonObjects = new JsonObjects<>();

        UserAccountEntityExample example = new UserAccountEntityExample();
        example.createCriteria().andRoleEqualTo(GlobalConstant.ROLE_COMMISSIONER);
        example.setOrderByClause(sortOrder + " " + sortOrder);
        List<UserAccountEntity> entityList = userAccountEntityMapper.selectByExample(example);
        long total = userAccountEntityMapper.countByExample(example);
        jsonObjects.setCurrentPage(pageNum);
        jsonObjects.setTotal(total);
        if (pageNum > 0 && pageSize > 0) {
            jsonObjects.setTotalPage(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        }
        if (entityList.size() > 0) {
            jsonObjects.getObjectElements().addAll(mapperListObjects(entityList, UserAccount.class));
        }
        return jsonObjects;
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        UserAccountEntity userAccountEntity = dozerMapper.map(userAccount, UserAccountEntity.class);
        userAccountEntityMapper.insert(userAccountEntity);
        return dozerMapper.map(userAccountEntity, UserAccount.class);
    }

    @Override
    public UserAccount retrieveById(long id) {
        UserAccountEntity userAccountEntity = userAccountEntityMapper.selectByPrimaryKey(id);
        if (userAccountEntity != null) {
            return dozerMapper.map(userAccountEntity, UserAccount.class);
        } else {
            return null;
        }
    }

    @Override
    public ResponseResult updateById(UserAccount userAccount) {
        UserAccountEntity userAccountEntity = userAccountEntityMapper.selectByPrimaryKey(Long.parseLong(userAccount.getId()));
        if (userAccountEntity != null) {
            userAccountEntity.setRecordStatus(userAccount.getRecordStatus());
            userAccountEntity.setUsername(userAccount.getUsername());
            userAccountEntity.setPassword(userAccount.getPassword());
            userAccountEntityMapper.updateByPrimaryKey(userAccountEntity);
        }
        return new ResponseResult();
    }

    @Override
    public ResponseResult deleteById(long id) {
        userAccountEntityMapper.deleteByPrimaryKey(id);
        return new ResponseResult();
    }
}
