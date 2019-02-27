package com.spring.oauthdemo.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration
{
    /*
       This method handles OAuth authentication code placed on top of Methods in Controllers
     */
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler()
    {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
