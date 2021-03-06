package com.revengemission.customerservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revengemission.customerservice.domain.ApplicationData;
import com.revengemission.customerservice.persistence.entity.DiscussionEntity;
import com.revengemission.customerservice.persistence.entity.DiscussionTopicEntity;
import com.revengemission.customerservice.persistence.mapper.DiscussionEntityMapper;
import com.revengemission.customerservice.persistence.mapper.DiscussionTopicEntityMapper;
import com.revengemission.customerservice.utils.JacksonJSONUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceApplicationTests {

    @Autowired
    ApplicationData applicationData;

    @Autowired
    DiscussionEntityMapper discussionEntityMapper;

    @Autowired
    DiscussionTopicEntityMapper discussionTopicEntityMapper;

    @Test
    @Ignore
    public void contextLoads() {
        applicationData.openAConversation();

        applicationData.onlineStaff(3l);
        applicationData.onlineStaff(1l);
        applicationData.onlineStaff(2l);

        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.setEnableNewConversation(false);
        applicationData.offlineStaff(1l);
        applicationData.closeAConversation(3l);

        Map<Long, Integer> result = applicationData.sortMapByValueAsc();
        result.forEach((k, v) -> {
            System.out.println("排序完后返回 map: " + k + "->" + v);
        });

        if (result.size() > 0) {
            System.out.println("K =" + applicationData.getFirstElement(result).getKey());
            System.out.println("V =" + applicationData.getFirstElement(result).getValue());
        }
    }


    @Ignore
    @Test
    @Transactional
    //@Rollback(value = false)
    public void discussionTest() throws JsonProcessingException {
        DiscussionTopicEntity discussionTopicEntity = new DiscussionTopicEntity();
        discussionTopicEntity.setTopic("月季花");
        discussionTopicEntityMapper.insert(discussionTopicEntity);

        DiscussionEntity discussionEntity = new DiscussionEntity();
        discussionEntity.setDiscussionTopicId(discussionTopicEntity.getId());
        discussionEntity.setUserId(1l);
        discussionEntity.setAuthor("张三");
        discussionEntity.setTitle("月季种植");
        discussionEntity.setContent("怎么种植，如何培育，如何过冬");
        List<String> list = new ArrayList<>();
        list.add("种植");
        list.add("培育");
        list.add("过冬");

        //discussionEntity.setTags("[\"种植\",\"培育\",\"过冬\"]");
        discussionEntity.setTags(JacksonJSONUtils.objectToJSONString(list));
        discussionEntityMapper.insert(discussionEntity);
        System.out.println("===========" + JacksonJSONUtils.objectToJSONString(discussionEntity));

    }
}
