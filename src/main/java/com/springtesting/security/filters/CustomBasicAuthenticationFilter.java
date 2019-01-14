package com.springtesting.security.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter
{
    public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        super(authenticationManager);
    }

    protected void onSuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, Authentication authResult) throws IOException
    {
        request.getSession(false);
        System.out.println("Session Id: "+request.getSession(false));
    }
}
