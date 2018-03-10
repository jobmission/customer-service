package com.revengemission.customerservice.service.impl;

import com.revengemission.customerservice.dao.entity.UserAccountEntity;
import com.revengemission.customerservice.dao.repertory.UserAccountRepository;
import com.revengemission.customerservice.domain.GlobalConstant;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.ResponseResult;
import com.revengemission.customerservice.domain.UserAccount;
import com.revengemission.customerservice.service.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl extends BaseServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserAccount login(String username, String password) {
        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(username);
        if (userAccountEntity != null) {
            return dozerMapper.map(userAccountEntity, UserAccount.class);
        } else {
            return null;
        }
    }

    @Override
    public JsonObjects<UserAccount> list(int pageNum, int pageSize, String sortField, String sortOrder) {
        JsonObjects<UserAccount> jsonObjects = new JsonObjects<>();
        Sort sort = null;
        if (StringUtils.equalsIgnoreCase("asc", sortOrder)) {
            sort = new Sort(Sort.Direction.ASC, sortField);
        } else {
            sort = new Sort(Sort.Direction.DESC, sortField);
        }
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<UserAccountEntity> userAccountEntityPage = userAccountRepository.listByRole(GlobalConstant.ROLE_ADMIN, pageRequest);
        jsonObjects.setCurrentPage(pageNum);
        jsonObjects.setTotal(userAccountEntityPage.getTotalElements());
        if (pageNum > 0 && pageSize > 0) {
            jsonObjects.setTotalPage(userAccountEntityPage.getTotalPages());
        }
        if (userAccountEntityPage.getContent().size() > 0) {
            jsonObjects.getObjectElements().addAll(mapperListObjects(userAccountEntityPage.getContent(), UserAccount.class));
        }
        return jsonObjects;
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        UserAccountEntity userAccountEntity = dozerMapper.map(userAccount, UserAccountEntity.class);
        userAccountEntity = userAccountRepository.save(userAccountEntity);
        return dozerMapper.map(userAccountEntity, UserAccount.class);
    }

    @Override
    public UserAccount retrieveById(long id) {
        Optional<UserAccountEntity> optional = userAccountRepository.findById(id);
        if (optional.isPresent()) {
            return dozerMapper.map(optional.get(), UserAccount.class);
        } else {
            return null;
        }
    }

    @Override
    public ResponseResult updateById(UserAccount userAccount) {
        Optional<UserAccountEntity> optional = userAccountRepository.findById(Long.parseLong(userAccount.getId()));
        optional.ifPresent(e -> {
            e.setRecordStatus(userAccount.getRecordStatus());
            e.setUsername(userAccount.getUsername());
            e.setPassword(userAccount.getPassword());
            userAccountRepository.save(e);
        });
        return new ResponseResult();
    }

    @Override
    public ResponseResult deleteById(long id) {
        userAccountRepository.deleteById(id);
        return new ResponseResult();
    }
}
