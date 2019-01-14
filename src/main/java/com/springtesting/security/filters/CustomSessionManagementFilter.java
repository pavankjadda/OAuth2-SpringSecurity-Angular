package com.springtesting.security.filters;

import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomSessionManagementFilter extends SessionManagementFilter
{
    public CustomSessionManagementFilter(SecurityContextRepository securityContextRepository)
    {
        super(securityContextRepository);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    {
        System.out.println("req.getLocalAddr() "+req.getLocalAddr());
    }
}
