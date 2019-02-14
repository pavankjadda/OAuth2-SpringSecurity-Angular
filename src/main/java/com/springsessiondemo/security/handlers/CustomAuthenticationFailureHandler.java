package com.springsessiondemo.security.handlers;

import com.springsessiondemo.constants.SecurityConstants;
import com.springsessiondemo.model.FailedLogin;
import com.springsessiondemo.repo.FailedLoginRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler
{
    protected Log logger = LogFactory.getLog(this.getClass());

    private FailedLoginRepository failedLoginRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public CustomAuthenticationFailureHandler(FailedLoginRepository failedLoginRepository)
    {
        this.failedLoginRepository=failedLoginRepository;
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
    {
        System.out.println("Inside onAuthenticationFailure() method: Login Failed, redirecting to Login Page");
        System.out.println("Exception: " + exception.toString());
        handle(request, response, exception);
        clearAuthenticationAttributes(request);
        saveRequesterInformation(request,response);

    }

    private void handle(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException
    {
        String targetUrl = determineTargetUrl(request,exception);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(HttpServletRequest request, AuthenticationException authenticationException)
    {
        return SecurityConstants.formFailureLoginUrl;
    }

    private void clearAuthenticationAttributes(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session == null)
            return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void saveRequesterInformation(HttpServletRequest request, HttpServletResponse response)
    {
        FailedLogin failedLogin=new FailedLogin();
        failedLogin.setRequesterIpAddress(request.getRemoteAddr());
        failedLogin.setRequesterPort(request.getRemotePort());
        failedLogin.setLoggedDataTime(LocalDateTime.now());
        failedLogin.setBrowserInformation(request.getAuthType());
        failedLogin.setAuthType(request.getAuthType());
        failedLogin.setBrowserInformation("request.getRemoteUser(): "+request.getRemoteUser()
                +"request.getLocalAddr(): "+request.getLocalAddr()+
                "request.getLocalPort(): "+request.getLocalPort()+
                "request.getServerName(): "+request.getServerName()+
                "request.getServerPort(): "+request.getServerPort()+
                "request.getHeaderNames(): "+request.getHeaderNames());

        try
        {
            failedLoginRepository.saveAndFlush(failedLogin);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
