package com.springtesting.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;

@Component
public class CustomHttpSessionEventPublisher
{
    private final HttpSessionEventPublisher httpSessionEventPublisher;

    private Logger logger= LoggerFactory.getLogger(CustomHttpSessionEventPublisher.class);

    @Autowired
    public CustomHttpSessionEventPublisher(HttpSessionEventPublisher httpSessionEventPublisher)
    {
        this.httpSessionEventPublisher = httpSessionEventPublisher;
    }


    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {
        HttpSessionCreatedEvent e = new HttpSessionCreatedEvent(httpSessionEvent.getSession());
        logger.info("sessionCreated => "+httpSessionEvent.getSession());
        //logger.info("sessionCreated => "+e.getSession());

    }

    /**
     * Handles the HttpSessionEvent by publishing a {@link HttpSessionDestroyedEvent} to
     * the application appContext.
     *
     * @param httpSessionEvent The HttpSessionEvent pass in by the container
     */
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {
        HttpSessionDestroyedEvent e = new HttpSessionDestroyedEvent(httpSessionEvent.getSession());
        logger.info("Session Destroyed => "+httpSessionEvent.getSession());
    }
}
