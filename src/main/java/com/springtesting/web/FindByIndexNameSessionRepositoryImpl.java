package com.springtesting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
    SessionRegistry implemented through FindByIndexNameSessionRepository

 */

@RestController
@RequestMapping("/admin/registry/v2")
public class FindByIndexNameSessionRepositoryImpl
{
    private FindByIndexNameSessionRepository findByIndexNameSessionRepository;

    @Autowired
    public FindByIndexNameSessionRepositoryImpl(FindByIndexNameSessionRepository findByIndexNameSessionRepository)
    {
        this.findByIndexNameSessionRepository = findByIndexNameSessionRepository;
    }

    @GetMapping(value = {"/users"})
    public Map getAllUsers()
    {
        return findByIndexNameSessionRepository.findByPrincipalName("admin");
    }
}