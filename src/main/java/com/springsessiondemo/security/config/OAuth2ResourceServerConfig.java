package com.springsessiondemo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    private static final String RESOURCE_ID = "resource-server-rest-api";

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.requestMatchers()
                .antMatchers("/api/**")
                .antMatchers("/profile/**")
                .antMatchers("/dba/**")
                .antMatchers("/register/**")
                .antMatchers("/users/**")
                .antMatchers("/dba/**")
            .and()
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .anyRequest().authenticated()
            .and()
                .httpBasic()
             .and()
                .exceptionHandling()
             .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll()
             .and()
                .logout()
                .deleteCookies("X-Auth-Token")
                .permitAll();
/*
        http.requestMatchers()
                .antMatchers("/api/**")
            .and()
                .authorizeRequests()
                .antMatchers("/api/**").hasRole("ADMIN");
        http.addFilterBefore(new BasicAuthenticationFilter(authenticationManagerBean), UsernamePasswordAuthenticationFilter.class);*/
    }
}
