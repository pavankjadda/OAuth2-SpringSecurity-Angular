package com.springsessiondemo.config;

import com.springsessiondemo.security.MyUserDetailsService;
import com.springsessiondemo.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(SecurityConfig.class)
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter
{
    private final AuthenticationManager authenticationManager;

    @Qualifier("dataSource")
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
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
    {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore()
    {
        return new JdbcTokenStore(dataSource);
    }

}
