package com.springsessiondemo.config;

import com.springsessiondemo.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@PropertySource("classpath:application-dev.properties")
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter
{
    private final AuthenticationManager authenticationManager;

    private final DataSource dataSource;

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public AuthServerOAuth2Config(AuthenticationManager authenticationManager, DataSource dataSource, MyUserDetailsService myUserDetailsService)
    {
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler()
    {
        return new OAuth2AccessDeniedHandler();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
    {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userDetailsService(myUserDetailsService);
    }

    @Bean
    public TokenStore tokenStore()
    {
        return new JdbcTokenStore(dataSource);
    }

}
