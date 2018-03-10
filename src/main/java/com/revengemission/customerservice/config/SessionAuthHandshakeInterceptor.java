package com.revengemission.customerservice.config;

import com.revengemission.customerservice.domain.ApplicationData;
import com.revengemission.customerservice.domain.Conversation;
import com.revengemission.customerservice.domain.GlobalConstant;
import com.revengemission.customerservice.service.ConversationService;
import com.revengemission.customerservice.utils.ClientIPUtil;
import com.revengemission.customerservice.utils.IdWorker;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SessionAuthHandshakeInterceptor implements HandshakeInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConversationService conversationService;

    @Autowired
    ApplicationData applicationData;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request.getPrincipal() == null) {
            logger.info("beforeHandshake 首次打开socket连接");


            HttpServletRequest httpServletRequest = getHttpServletRequest(request);
            Long userId = IdWorker.getSingleton(1, 1).nextId();

            //匿名登陆
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            authList.add(new SimpleGrantedAuthority(GlobalConstant.ROLE_ANONYMOUS));
            ConversationPrincipal conversationPrincipal = new ConversationPrincipal(userId, null, authList);

            Long recipientId = applicationData.openAConversation();
            if (recipientId != null) {
                Conversation conversation = new Conversation();
                conversation.setInitiatorId(userId + "");
                conversation.setRecipientId(recipientId + "");
                UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeaders().get("User-Agent").toString());
                conversation.setIp(ClientIPUtil.getIpAddress(httpServletRequest));
                conversation.setOs(userAgent.getOperatingSystem().getName());
                conversation.setBrowser(userAgent.getBrowser().getName());
                conversation = conversationService.create(conversation);
                conversationPrincipal.setConversationId(Long.parseLong(conversation.getId()));
                conversationPrincipal.setRecipientId(recipientId);
                attributes.put("recipientId", recipientId);
            }

            SecurityContextHolder.getContext().setAuthentication(conversationPrincipal);
            httpServletRequest.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        } else {
            logger.info("beforeHandshake 已登录 " + request.getPrincipal().getName());
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        if (request.getPrincipal() == null) {
            logger.info("afterHandshake 不正常!");
        }
    }

    // 参考 HttpSessionHandshakeInterceptor
    private HttpServletRequest getHttpServletRequest(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest();
        }
        return null;
    }

}
