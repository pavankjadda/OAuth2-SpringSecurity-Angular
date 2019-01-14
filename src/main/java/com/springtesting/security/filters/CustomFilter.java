package com.springtesting.security.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
public class CustomFilter extends GenericFilterBean
{
    private Logger logger= LoggerFactory.getLogger(CustomFilter.class);

    SessionManagementFilter sessionManagementFilter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        logger.info("Inside CustomFilter doFilter() method");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy()
    {
        logger.info("Inside CustomFilter destroy() method");

    }
}
