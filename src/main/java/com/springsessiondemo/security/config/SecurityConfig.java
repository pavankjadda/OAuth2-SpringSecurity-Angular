package com.springsessiondemo.security.config;


import com.springsessiondemo.config.Encoders;
import com.springsessiondemo.repo.FailedLoginRepository;
import com.springsessiondemo.repo.SessionHistoryRepository;
import com.springsessiondemo.security.MyUserDetailsService;
import com.springsessiondemo.security.providers.CustomDaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;


@Configuration
@EnableWebSecurity
@Order(SecurityProperties.IGNORED_ORDER)
@Import(Encoders.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final MyUserDetailsService userDetailsService;

    private final SessionHistoryRepository sessionHistoryRepository;

    private final FailedLoginRepository failedLoginRepository;

    @Qualifier("userPasswordEncoder")
    private final PasswordEncoder userPasswordEncoder;

    @Autowired
    public SecurityConfig(MyUserDetailsService userDetailsService, SessionHistoryRepository sessionHistoryRepository, FailedLoginRepository failedLoginRepository, PasswordEncoder userPasswordEncoder)
    {
        this.userDetailsService = userDetailsService;
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.failedLoginRepository=failedLoginRepository;
        this.userPasswordEncoder = userPasswordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

  /* @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception
   {
       auth.inMemoryAuthentication().withUser("john").password("123").roles("USER");
   }*/
    @Bean
    public CustomDaoAuthenticationProvider getDaoAuthenticationProvider()
    {
        CustomDaoAuthenticationProvider daoAuthenticationProvider = new CustomDaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    /* BCrypt strength should 12 or more*/
    @Bean
    public PasswordEncoder getBCryptPasswordEncoder()
    {
        return userPasswordEncoder;
    }


    @Bean
    public SpringSessionRememberMeServices springSessionRememberMeServices()
    {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setRememberMeParameterName("remember-me");
        rememberMeServices.setValiditySeconds(ApplicationConstants.rememberMeTimeOut);
        return rememberMeServices;
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


    @Bean
    SessionRegistry sessionRegistry()
    {
        return new SessionRegistryImpl();
    }


    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring().antMatchers( "/static/**","/resources/**", "/js/**", "/css/**", "/images/**");

    }



    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}
