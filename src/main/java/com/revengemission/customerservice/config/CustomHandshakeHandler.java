package com.revengemission.customerservice.config;

import com.revengemission.customerservice.domain.GlobalConstant;
import com.revengemission.customerservice.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request.getPrincipal() == null) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
            authList.add(new SimpleGrantedAuthority(GlobalConstant.ROLE_ANONYMOUS));
            /*User user = new User(servletRequest.getServletRequest()
                    .getSession().getId(), "password", authList);

			// 根据userDetails构建新的Authentication,这里使用了
			// PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken
			PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(
					user, user.getPassword(), user.getAuthorities());

			// 设置authentication中details
			authentication.setDetails(new WebAuthenticationDetails(
					servletRequest.getServletRequest()));

			// 存放authentication到SecurityContextHolder
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			HttpSession session = servletRequest.getServletRequest()
					.getSession(true);
			// 在session中存放security context,方便同一个session中控制用户的其他操作
			session.setAttribute("SPRING_SECURITY_CONTEXT",
					SecurityContextHolder.getContext());*/
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(IdWorker.getSingleton(1, 1).nextId(), null, authList);
            SecurityContextHolder.getContext().setAuthentication(token);
            servletRequest.getServletRequest().getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            log.info("anonymous login");

            // return principal;
            return super.determineUser(request, wsHandler, attributes);
        } else {
            return super.determineUser(request, wsHandler, attributes);
        }
    }


}
