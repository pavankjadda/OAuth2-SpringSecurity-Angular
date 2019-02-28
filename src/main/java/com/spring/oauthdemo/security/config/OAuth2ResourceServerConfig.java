package com.spring.oauthdemo.security.config;

import com.spring.oauthdemo.security.constants.ResourceConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

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
            .and()
                .authorizeRequests()
                .anyRequest().authenticated()
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
        http.csrf()
                .disable();
        http.cors()
                .configurationSource(corsConfigurationSource());
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
