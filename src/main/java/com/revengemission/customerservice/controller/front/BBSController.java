package com.revengemission.customerservice.controller.front;

import com.revengemission.customerservice.domain.Discussion;
import com.revengemission.customerservice.domain.DiscussionTopic;
import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.service.DiscussionService;
import com.revengemission.customerservice.service.DiscussionTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bbs")
public class BBSController {

    @Autowired
    DiscussionTopicService discussionTopicService;

    @Autowired
    DiscussionService discussionService;

    @GetMapping(value = {"", "/", "/latest"})
    public String index(Model model, HttpServletRequest request) {
        JsonObjects<DiscussionTopic> discussionTopicJsonObjects = discussionTopicService.list(-1, -1, "sort_priority", "desc");
        if (discussionTopicJsonObjects != null && discussionTopicJsonObjects.getObjectElements() != null) {
            model.addAttribute("topicElements", discussionTopicJsonObjects.getObjectElements());
        }

        JsonObjects<Discussion> discussionJsonObjects = discussionService.listDiscussion(null, 1, 20);

        if (discussionJsonObjects != null && discussionJsonObjects.getObjectElements() != null) {
            model.addAttribute("discussionElements", discussionJsonObjects.getObjectElements());
        }
        model.addAttribute("menuTab", "latest");
        return "front/bbs";
    }

    @GetMapping(value = "/latest/{topicId}")
    public String listByTopic(Model model, HttpServletRequest request, @PathVariable("topicId") long topicId) {
        JsonObjects<DiscussionTopic> discussionTopicJsonObjects = discussionTopicService.list(-1, -1, "sort_priority", "desc");
        if (discussionTopicJsonObjects != null && discussionTopicJsonObjects.getObjectElements() != null) {
            model.addAttribute("topicElements", discussionTopicJsonObjects.getObjectElements());
        }

        JsonObjects<Discussion> discussionJsonObjects = discussionService.listDiscussion(topicId, 1, 20);

        if (discussionJsonObjects != null && discussionJsonObjects.getObjectElements() != null) {
            model.addAttribute("discussionElements", discussionJsonObjects.getObjectElements());
        }
        model.addAttribute("menuTab", "latest");
        return "front/bbs";
    }

    @GetMapping(value = "/best")
    public String bestTopic(Model model, HttpServletRequest request) {
        JsonObjects<DiscussionTopic> discussionTopicJsonObjects = discussionTopicService.list(-1, -1, "sort_priority", "desc");
        if (discussionTopicJsonObjects != null && discussionTopicJsonObjects.getObjectElements() != null) {
            model.addAttribute("topicElements", discussionTopicJsonObjects.getObjectElements());
        }

        JsonObjects<Discussion> discussionJsonObjects = discussionService.listBestDiscussion(1, 20);

        if (discussionJsonObjects != null && discussionJsonObjects.getObjectElements() != null) {
            model.addAttribute("discussionElements", discussionJsonObjects.getObjectElements());
        }
        model.addAttribute("menuTab", "best");
        return "front/bbs";
    }

    @GetMapping(value = {"/publish"})
    public String publish(Model model, HttpServletRequest request) {
        JsonObjects<DiscussionTopic> discussionTopicJsonObjects = discussionTopicService.list(-1, -1, "sort_priority", "desc");
        if (discussionTopicJsonObjects != null && discussionTopicJsonObjects.getObjectElements() != null) {
            model.addAttribute("topicElements", discussionTopicJsonObjects.getObjectElements());
        }
        return "front/publish";
    }

    @ResponseBody
    @PostMapping(value = {"/publish"})
    public Map<String, Object> handlePublish(Principal principal,
                                             @RequestParam(value = "discussionTopicId") long discussionTopicId,
                                             @RequestParam(value = "title") String title,
                                             @RequestParam(value = "content") String content) {

        Map<String, Object> result = new HashMap<>();
        Discussion discussion = new Discussion();
        discussion.setUserId(Long.parseLong(principal.getName()));
        discussion.setTitle(title);
        discussion.setContent(content);
        discussion.setDiscussionTopicId(discussionTopicId);
        discussion.setDateCreated(new Date());
        discussionService.create(discussion);
        result.put("status", 1);
        return result;
    }
}
