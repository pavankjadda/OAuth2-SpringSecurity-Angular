package com.springsessiondemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SessionLogUtil
{
    Logger logger=LoggerFactory.getLogger(SessionLogUtil.class);

    private final HttpSessionEventPublisher httpSessionEventPublisher;


    @Autowired
    public SessionLogUtil(HttpSessionEventPublisher httpSessionEventPublisher)
    {
        this.httpSessionEventPublisher = httpSessionEventPublisher;
    }

    
}
