package com.spring.oauthdemo.web;

import com.spring.oauthdemo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/registry/v1")
public class SessionRegistryImplController
{
    private SessionRegistry sessionRegistry;

    @Autowired
    public SessionRegistryImplController(SessionRegistry sessionRegistry)
    {
        this.sessionRegistry = sessionRegistry;
    }

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
    }
}
