package com.spring.oauthdemo.security.config;


import com.spring.oauthdemo.security.MyUserDetailsService;
import com.spring.oauthdemo.security.constants.ApplicationConstants;
import com.spring.oauthdemo.security.providers.CustomDaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;


@Configuration
@EnableWebSecurity
//@Order(1)
@Import(PasswordEncoders.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final MyUserDetailsService userDetailsService;


    @Qualifier("userPasswordEncoder")
    private final PasswordEncoder userPasswordEncoder;

    @Autowired
    public SecurityConfig(MyUserDetailsService userDetailsService, PasswordEncoder userPasswordEncoder)
    {
        this.userDetailsService = userDetailsService;
        this.userPasswordEncoder = userPasswordEncoder;
    }


   /* @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/register").permitAll()
                .antMatchers("/users/**").hasAuthority(AuthorityConstants.Admin)
                .antMatchers("/admin**").hasAuthority(AuthorityConstants.Admin)
                .antMatchers("/profile/**").hasAuthority(AuthorityConstants.User)
                //.antMatchers("/api/**").hasAnyAuthority(AuthorityConstants.ApiUser,AuthorityConstants.Admin)
                .antMatchers("/dba/**").hasAuthority(AuthorityConstants.Dba)
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
    }*/

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
/*
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
    }*/


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
