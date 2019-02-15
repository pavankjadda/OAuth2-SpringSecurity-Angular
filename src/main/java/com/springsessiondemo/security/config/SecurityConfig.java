package com.springsessiondemo.security.config;


import com.springsessiondemo.repo.FailedLoginRepository;
import com.springsessiondemo.repo.SessionHistoryRepository;
import com.springsessiondemo.security.MyUserDetailsService;
import com.springsessiondemo.security.handlers.CustomAuthenticationFailureHandler;
import com.springsessiondemo.security.handlers.CustomAuthenticationSuccessHandler;
import com.springsessiondemo.security.handlers.CustomLogoutSuccessHandler;
import com.springsessiondemo.security.providers.CustomDaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final MyUserDetailsService userDetailsService;

    private final SessionHistoryRepository sessionHistoryRepository;

    private final FailedLoginRepository failedLoginRepository;


    @Autowired
    public SecurityConfig(MyUserDetailsService userDetailsService, SessionHistoryRepository sessionHistoryRepository,FailedLoginRepository failedLoginRepository)
    {
        this.userDetailsService = userDetailsService;
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.failedLoginRepository=failedLoginRepository;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

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
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                    .antMatchers("/anonymous*").anonymous()
                    //.antMatchers("/users/**").permitAll()
                    .antMatchers("/users/**").hasAuthority(AuthorityConstants.Admin)
                    .antMatchers("/admin**").hasAuthority(AuthorityConstants.Admin)
                    .antMatchers("/profile/**").hasAuthority(AuthorityConstants.User)
                    .antMatchers("/api/**").hasAnyAuthority(AuthorityConstants.ApiUser, AuthorityConstants.Admin)
                    .antMatchers("/dba/**").hasAuthority(AuthorityConstants.Dba)
                    .anyRequest().authenticated()
                .and()
                    .httpBasic()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .successHandler(new CustomAuthenticationSuccessHandler(sessionHistoryRepository))
                    .failureHandler(new CustomAuthenticationFailureHandler(failedLoginRepository))
                    .permitAll()
                .and()
                    .logout()
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                    .permitAll()
                .and()
                    .rememberMe().rememberMeServices(springSessionRememberMeServices());


        http.cors();

        http.sessionManagement()
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry());

        http.csrf()
                .disable();
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
        web.ignoring()
                .antMatchers( "/static/**","/resources/**", "/js/**", "/css/**", "/images/**");

    }



    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}