package com.wrobank.banking.infrastructure.application;

import com.wrobank.banking.user.domain.User;
import com.wrobank.banking.user.services.UserInMemoryService;
import com.wrobank.banking.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

import java.util.function.Consumer;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Bean
    DefaultCorsFilter corsFilter() {
        return new DefaultCorsFilter();
    }

    @Bean
    public UserService userService() {
        return new UserInMemoryService();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        configureUsers(auth.inMemoryAuthentication());
    }

    private void configureUsers(UserDetailsManagerConfigurer userDetailsManagerConfigurer) {
        Consumer<User> userConfigurer = (User user) -> configureUser(userDetailsManagerConfigurer, user);
        userService().findAllUsers().forEach(userConfigurer);
    }

    private void configureUser(UserDetailsManagerConfigurer userDetailsManagerConfigurer, User user) {
        userDetailsManagerConfigurer
                .withUser(user.getUserName())
                .password("{noop}pass")
                .roles("USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
                    .antMatchers("/index.html", "/", "/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
