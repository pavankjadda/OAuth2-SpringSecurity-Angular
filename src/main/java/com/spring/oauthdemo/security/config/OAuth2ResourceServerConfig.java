package com.spring.oauthdemo.security.config;

import com.spring.oauthdemo.security.constants.ResourceConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(ResourceConstants.RESOURCE_SERVER_REST_API);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        //requestMatchers() matches authentication to OAuth
        http.requestMatchers()
                .antMatchers("/api/**")
                .antMatchers("/profile/**")
                .antMatchers("/dba/**")
                .antMatchers("/register/**")
                .antMatchers("/users/**")
                .antMatchers("/dba/**")

           //From this point on wards custom authentication like Basic or Form auth used
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

    }
}
