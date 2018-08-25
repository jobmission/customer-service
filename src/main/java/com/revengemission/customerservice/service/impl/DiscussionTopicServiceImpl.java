package com.revengemission.customerservice.service.impl;

import com.revengemission.customerservice.domain.DiscussionTopic;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.persistence.entity.DiscussionTopicEntity;
import com.revengemission.customerservice.persistence.entity.DiscussionTopicEntityExample;
import com.revengemission.customerservice.persistence.mapper.DiscussionTopicEntityMapper;
import com.revengemission.customerservice.service.DiscussionTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionTopicServiceImpl extends BaseServiceImpl implements DiscussionTopicService {
    @Autowired
    DiscussionTopicEntityMapper discussionTopicEntityMapper;

    @Override
    public JsonObjects<DiscussionTopic> list(int pageNum, int pageSize, String sortField, String sortOrder) {
        JsonObjects<DiscussionTopic> jsonObjects = new JsonObjects<>();

        DiscussionTopicEntityExample example = new DiscussionTopicEntityExample();
        example.addOrderBy(sortField, sortOrder);
        List<DiscussionTopicEntity> entityList = discussionTopicEntityMapper.selectByExample(example);
        if (entityList.size() > 0) {
            jsonObjects.getObjectElements().addAll(mapperListObjects(entityList, DiscussionTopic.class));
        }
        return jsonObjects;
    }
}
