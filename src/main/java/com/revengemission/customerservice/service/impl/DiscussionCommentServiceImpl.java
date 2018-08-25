package com.revengemission.customerservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.revengemission.customerservice.domain.DiscussionComment;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.ResponseResult;
import com.revengemission.customerservice.persistence.entity.DiscussionCommentEntity;
import com.revengemission.customerservice.persistence.entity.DiscussionCommentEntityExample;
import com.revengemission.customerservice.persistence.mapper.DiscussionCommentEntityMapper;
import com.revengemission.customerservice.service.DiscussionCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionCommentServiceImpl extends BaseServiceImpl implements DiscussionCommentService {
    @Autowired
    DiscussionCommentEntityMapper discussionCommentEntityMapper;

    @Override
    public JsonObjects<DiscussionComment> listDiscussionComment(long discussionId, int pageNum, int pageSize) {
        JsonObjects<DiscussionComment> jsonObjects = new JsonObjects<>();

        DiscussionCommentEntityExample example = new DiscussionCommentEntityExample();
        example.createCriteria().andDiscussionIdEqualTo(discussionId);
        if (pageNum > 0 && pageSize > 0) {
            PageHelper.startPage(pageNum, pageSize, false);
        }
        List<DiscussionCommentEntity> entityList = discussionCommentEntityMapper.selectByExample(example);
        long total = discussionCommentEntityMapper.countByExample(example);
        jsonObjects.setCurrentPage(pageNum);
        jsonObjects.setTotal(total);
        if (pageNum > 0 && pageSize > 0) {
            jsonObjects.setTotalPage(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        }
        if (entityList.size() > 0) {
            jsonObjects.getObjectElements().addAll(mapperListObjects(entityList, DiscussionComment.class));
        }
        return jsonObjects;
    }

    @Override
    public DiscussionComment create(DiscussionComment discussionComment) {
        DiscussionCommentEntity discussionCommentEntity = dozerMapper.map(discussionComment, DiscussionCommentEntity.class);
        discussionCommentEntityMapper.insert(discussionCommentEntity);
        return dozerMapper.map(discussionCommentEntity, DiscussionComment.class);
    }

    @Override
    public ResponseResult deleteById(long id) {
        discussionCommentEntityMapper.deleteByPrimaryKey(id);
        return new ResponseResult();
    }
}
