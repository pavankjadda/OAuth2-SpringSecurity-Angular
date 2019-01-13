package com.springtesting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RawConsumer
{
    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    void consume()
    {
        Session session = (Session) this.sessionRepository
                .findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "principal")
                .values().iterator().next();
        session.setAttribute("test", UUID.randomUUID().toString());
        this.sessionRepository.save(session);
    }

}