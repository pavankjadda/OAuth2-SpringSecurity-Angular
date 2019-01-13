package com.springtesting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/session-registry")
public class SessionRegistryImplController
{
    private FindByIndexNameSessionRepository findByIndexNameSessionRepository;

    @Autowired
    public SessionRegistryImplController(FindByIndexNameSessionRepository findByIndexNameSessionRepository)
    {
        this.findByIndexNameSessionRepository = findByIndexNameSessionRepository;
    }

    @GetMapping(value = {"/users"})
    public Map getAllUsers()
    {
        return findByIndexNameSessionRepository.findByPrincipalName("admin");
    }

    /*
    @GetMapping(value = {"/users"})
    public List<String> getAllUsers()
    {
        return sessionRegistry.getAllPrincipals().stream()
                .filter(user -> !sessionRegistry.getAllSessions(user, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @GetMapping(value = {"/active-users"})
    public List<String> getActiveUsers()
    {
        return sessionRegistry.getAllPrincipals().stream()
                .filter(user -> !sessionRegistry.getAllSessions(user, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
    }


    @GetMapping(value = {"/active-users/{username}"})
    public List<SessionInformation> getUserSessions(@PathVariable String username)
    {
        if(sessionRegistry.getAllPrincipals().size()>0)
        {
            MyUserDetails myUserDetails = (MyUserDetails) sessionRegistry.getAllPrincipals().get(0);
            if (myUserDetails.getUsername().equals(username))
                return sessionRegistry.getAllSessions(myUserDetails, false);
        }
        return null;
    }*/
}
