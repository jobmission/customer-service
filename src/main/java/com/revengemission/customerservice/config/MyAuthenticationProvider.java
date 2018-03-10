package com.revengemission.customerservice.config;

import com.revengemission.customerservice.domain.ApplicationData;
import com.revengemission.customerservice.domain.SessionData;
import com.revengemission.customerservice.domain.UserAccount;
import com.revengemission.customerservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserAccountService userService;

    @Autowired
    SessionData sessionData;

    @Autowired
    ApplicationData applicationData;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserAccount userAccount = userService.login(username, password);
        if (userAccount == null) {
            throw new BadCredentialsException("Username not found.");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        //加密过程在这里体现
        /*if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }*/
        if (!bCryptPasswordEncoder.matches(password, userAccount.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        applicationData.onlineStaff(Long.parseLong(userAccount.getId()));
        Collection<? extends GrantedAuthority> authorities = getAuthorities(userAccount);
        //Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        ConversationPrincipal conversationPrincipal = new ConversationPrincipal(userAccount.getId(), password, authorities);
        return conversationPrincipal;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(UserAccount user) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
    }
}
