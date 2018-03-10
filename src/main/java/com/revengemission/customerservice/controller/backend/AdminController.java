package com.revengemission.customerservice.controller.backend;

import com.revengemission.customerservice.config.ConversationPrincipal;
import com.revengemission.customerservice.controller.BaseController;
import com.revengemission.customerservice.domain.Conversation;
import com.revengemission.customerservice.domain.ConversationMessage;
import com.revengemission.customerservice.domain.MessageType;
import com.revengemission.customerservice.service.ConversationMessageService;
import com.revengemission.customerservice.service.ConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    ConversationMessageService conversationMessageService;

    @Autowired
    ConversationService conversationService;

    @GetMapping(value = {"", "/"})
    public String index(Model model, ConversationPrincipal principal) {
        List<Conversation> conversationEntityList = conversationService.findByRecipientIdAndStatusGreaterThanEqual(Long.parseLong(principal.getName()), 0);
        if (conversationEntityList != null) {
            model.addAttribute("conversationList", conversationEntityList);
        }
        return "admin/index";
    }

    /*
    * queue
    * */
    @MessageMapping("/reply")
    public void reply(ConversationMessage message, ConversationPrincipal principal) throws Exception {
        ConversationMessage conversationMessage = new ConversationMessage();
        conversationMessage.setConversationId(message.getConversationId());
        conversationMessage.setMessageType(MessageType.QUEUE.name());
        conversationMessage.setMessageFrom(principal.getName());
        conversationMessage.setMessageTo(message.getMessageTo());
        conversationMessage.setMessage(message.getMessage());
        conversationMessageService.create(conversationMessage);
        message.setDate(new Date());
        messagingTemplate.convertAndSendToUser(message.getMessageTo(),
                "/queue/talk", message);
    }

}
