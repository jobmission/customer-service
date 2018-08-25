package com.revengemission.customerservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.revengemission.customerservice.domain.Discussion;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.ResponseResult;
import com.revengemission.customerservice.persistence.entity.DiscussionEntity;
import com.revengemission.customerservice.persistence.entity.DiscussionEntityExample;
import com.revengemission.customerservice.persistence.mapper.DiscussionEntityMapper;
import com.revengemission.customerservice.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl extends BaseServiceImpl implements DiscussionService {
    @Autowired
    DiscussionEntityMapper discussionEntityMapper;

    @Override
    public JsonObjects<Discussion> listDiscussion(Long discussionTopicId, int pageNum, int pageSize) {
        JsonObjects<Discussion> jsonObjects = new JsonObjects<>();

        DiscussionEntityExample example = new DiscussionEntityExample();
        if (discussionTopicId != null) {
            example.createCriteria().andDiscussionTopicIdEqualTo(discussionTopicId);
        }
        if (pageNum > 0 && pageSize > 0) {
            PageHelper.startPage(pageNum, pageSize, false);
        }
        List<DiscussionEntity> entityList = discussionEntityMapper.selectByExample(example);
        long total = discussionEntityMapper.countByExample(example);
        jsonObjects.setCurrentPage(pageNum);
        jsonObjects.setTotal(total);
        if (pageNum > 0 && pageSize > 0) {
            jsonObjects.setTotalPage(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        }
        if (entityList.size() > 0) {
            jsonObjects.getObjectElements().addAll(mapperListObjects(entityList, Discussion.class));
        }
        return jsonObjects;
    }

    @Override
    public JsonObjects<Discussion> listBestDiscussion(int pageNum, int pageSize) {
        JsonObjects<Discussion> jsonObjects = new JsonObjects<>();

        DiscussionEntityExample example = new DiscussionEntityExample();
        example.createCriteria().andSortPriorityGreaterThan(0);
        if (pageNum > 0 && pageSize > 0) {
            PageHelper.startPage(pageNum, pageSize, false);
        }
        List<DiscussionEntity> entityList = discussionEntityMapper.selectByExample(example);
        long total = discussionEntityMapper.countByExample(example);
        jsonObjects.setCurrentPage(pageNum);
        jsonObjects.setTotal(total);
        if (pageNum > 0 && pageSize > 0) {
            jsonObjects.setTotalPage(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        }
        if (entityList.size() > 0) {
            jsonObjects.getObjectElements().addAll(mapperListObjects(entityList, Discussion.class));
        }
        return jsonObjects;
    }

    @Override
    public Discussion create(Discussion discussion) {
        DiscussionEntity discussionEntity = dozerMapper.map(discussion, DiscussionEntity.class);
        discussionEntityMapper.insert(discussionEntity);
        return dozerMapper.map(discussionEntity, Discussion.class);
    }

    @Override
    public Discussion retrieveById(long id) {
        DiscussionEntity discussionEntity = discussionEntityMapper.selectByPrimaryKey(id);
        if (discussionEntity != null) {
            return dozerMapper.map(discussionEntity, Discussion.class);
        } else {
            return null;
        }
    }

    @Override
    public ResponseResult deleteById(long id) {
        discussionEntityMapper.deleteByPrimaryKey(id);
        return new ResponseResult();
    }
}
