package com.springsessiondemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import java.util.UUID;

public class ParameterizedConsumer<S extends Session>
{

    @Autowired
    private FindByIndexNameSessionRepository<S> sessionRepository;

    public FindByIndexNameSessionRepository getSessionRepository()
    {
        return this.sessionRepository;
    }


    void consume()
    {
        S session = this.sessionRepository
                .findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "principal")
                .values().iterator().next();
        session.setAttribute("test", UUID.randomUUID().toString());
        this.sessionRepository.save(session);
    }


}
