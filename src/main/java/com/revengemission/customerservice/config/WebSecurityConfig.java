package com.revengemission.customerservice.config;

import com.revengemission.customerservice.domain.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider provider;//自定义验证

    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //将验证过程交给自定义验证工具
        auth.authenticationProvider(provider);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .cacheControl().disable()
                .and()
                // session的创建时机
                .sessionManagement().maximumSessions(1).and().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()//allow CORS option calls
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority(GlobalConstant.ROLE_COMMISSIONER, GlobalConstant.ROLE_ADMIN, GlobalConstant.ROLE_SUPER)
                .antMatchers("/bbs/publish**").hasAnyAuthority(GlobalConstant.ROLE_USER, GlobalConstant.ROLE_COMMISSIONER, GlobalConstant.ROLE_ADMIN, GlobalConstant.ROLE_SUPER)
                .antMatchers("/bbs/comment**").hasAnyAuthority(GlobalConstant.ROLE_USER, GlobalConstant.ROLE_COMMISSIONER, GlobalConstant.ROLE_ADMIN, GlobalConstant.ROLE_SUPER)
                /*.anyRequest().authenticated()*/
                .and()
                .formLogin()
                .loginProcessingUrl("/security_check")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .loginPage("/login")
                .permitAll();


        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true).logoutSuccessHandler(customLogoutSuccessHandler);

        http.sessionManagement().maximumSessions(2).sessionRegistry(sessionRegistry());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**");
    }


}
