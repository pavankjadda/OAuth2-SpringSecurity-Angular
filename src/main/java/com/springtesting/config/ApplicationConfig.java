package com.springtesting.config;

import com.springtesting.security.ParameterizedConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer
{
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SpringSessionBackedSessionRegistry springSessionBackedSessionRegistry()
    {
        return (SpringSessionBackedSessionRegistry) new ParameterizedConsumer<>().getSessionRepository();
    }

}
