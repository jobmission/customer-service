package com.revengemission.customerservice.controller.front;

import com.revengemission.customerservice.config.ConversationPrincipal;
import com.revengemission.customerservice.domain.*;
import com.revengemission.customerservice.service.ConversationMessageService;
import com.revengemission.customerservice.service.ConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class IndexController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    ConversationMessageService conversationMessageService;

    @Autowired
    ConversationService conversationService;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model, HttpServletRequest request) {
        return "index";
    }

    @ResponseBody
    @PostMapping(value = "refreshSession")
    public ResponseResult refreshSession(Model model, HttpServletRequest request) {
        return new ResponseResult();
    }

    /*
    * queue
    * */
    @MessageMapping("/send")
    public void send(ConversationMessage message, ConversationPrincipal principal) throws Exception {
        message.setDate(new Date());
        message.setMessageType(MessageType.QUEUE.name());
        message.setConversationId(principal.getConversationId());

        ConversationMessage conversationMessage = new ConversationMessage();
        conversationMessage.setConversationId(principal.getConversationId());
        conversationMessage.setMessageType(message.getMessageType());
        conversationMessage.setMessageFrom(principal.getName());
        conversationMessage.setMessage(message.getMessage());
        conversationMessage.setStatus(0);
        conversationMessageService.create(conversationMessage);

        messagingTemplate.convertAndSendToUser("" + principal.getRecipientId(),
                "/queue/conversations/message", message);
    }


    /*
    * queue
    * */
    @MessageMapping("/setUsername")
    public void setUsername(Conversation temp, ConversationPrincipal principal) throws Exception {
        log.info("principal " + principal.getPrincipal());
        ResponseResult responseResult = new ResponseResult();

        if (principal.getConversationId() != null) {
            Conversation conversation = conversationService.retrieveById(principal.getConversationId());
            if (conversation != null) {
                conversation.setUsername(temp.getUsername());
                conversationService.updateById(conversation);
                responseResult.setId(principal.getRecipientId());
                responseResult.setStatus(GlobalConstant.SUCCESS);
                //  客服添加新会话
                messagingTemplate.convertAndSendToUser("" + principal.getRecipientId(),
                        "/queue/conversations", conversation);
            }
        } else {
            responseResult.setStatus(GlobalConstant.ERROR);
            responseResult.setMessage("暂时没有线上客服,请稍后再试!");
        }

        //  新会话
        messagingTemplate.convertAndSendToUser(principal.getName(),
                "/queue/conversation/establish", responseResult);
    }

    /*
    * topic2
    * */
    @Scheduled(fixedRate = 1000 * 60 * 5)
    public void announce() throws Exception {
        // 发现消息
        ConversationMessage message = new ConversationMessage();
        message.setMessageType(MessageType.TOPIC.name());
        message.setDate(new Date());
        message.setMessage("会话维持");
        messagingTemplate.convertAndSend("/topic/announce", message);
    }

}
