package com.revengemission.customerservice.config;

import com.revengemission.customerservice.domain.ApplicationData;
import com.revengemission.customerservice.domain.Conversation;
import com.revengemission.customerservice.domain.GlobalConstant;
import com.revengemission.customerservice.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.security.Principal;
import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    HandshakeInterceptor handshakeInterceptor;

    @Autowired
    ApplicationData applicationData;

    @Autowired
    ConversationService conversationService;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //点对点式增加一个/queue 消息代理
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.
                addEndpoint("/my-websocket").
                addInterceptors(handshakeInterceptor).
                setHandshakeHandler(new CustomHandshakeHandler()).
                setAllowedOrigins("*").
                withSockJS().setClientLibraryUrl("/assets/stomp.min.js");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(final WebSocketHandler handler) {
                return new WebSocketHandlerDecorator(handler) {
                    @Override
                    public void afterConnectionEstablished(WebSocketSession session) throws Exception { // 客户端与服务器端建立连接后，此处记录谁上线了
                        Principal principal = session.getPrincipal();
                        if (principal instanceof ConversationPrincipal) {
                            if (((ConversationPrincipal) principal).getAuthorities().contains(new SimpleGrantedAuthority(GlobalConstant.ROLE_ANONYMOUS))) {
                            } else {
                                applicationData.onlineStaff(Long.parseLong(session.getPrincipal().getName()));
                            }
                        }
                        super.afterConnectionEstablished(session);
                    }

                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        // 客户端与服务器端断开连接后，此处记录谁下线了
                        Principal principal = session.getPrincipal();
                        if (principal instanceof ConversationPrincipal) {
                            if (((ConversationPrincipal) principal).getAuthorities().contains(new SimpleGrantedAuthority(GlobalConstant.ROLE_ANONYMOUS))) {
                                Object recipientId = session.getAttributes().get("recipientId");
                                Long conversationId = ((ConversationPrincipal) principal).getConversationId();
                                if (conversationId != null) {
                                    Conversation conversation = conversationService.retrieveById(conversationId);
                                    conversation.setStatus(-1);
                                    conversationService.updateById(conversation);
                                }
                                if (recipientId != null) {
                                    applicationData.closeAConversation(Long.parseLong(recipientId.toString()));
                                }
                            } else {
                                applicationData.onlineStaff(Long.parseLong(session.getPrincipal().getName()));
                            }
                        }
                        super.afterConnectionClosed(session, closeStatus);
                    }
                };
            }
        });
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        messageConverters.add(mappingJackson2MessageConverter);
        return true;
    }
}
