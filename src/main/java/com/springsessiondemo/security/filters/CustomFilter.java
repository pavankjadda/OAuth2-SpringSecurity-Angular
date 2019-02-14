package com.springsessiondemo.security.filters;

import com.springsessiondemo.model.SessionHistory;
import com.springsessiondemo.repo.SessionHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

//@Configuration
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class CustomFilter implements Filter
{
    private Logger logger= LoggerFactory.getLogger(CustomFilter.class);

    @Autowired
    private SessionHistoryRepository sessionHistoryRepository;

   /* @Autowired
    public CustomFilter(SessionHistoryRepository sessionHistoryRepository)
    {
        this.sessionHistoryRepository = sessionHistoryRepository;
    }*/
    public CustomFilter()
    {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        logger.info("Inside CustomFilter doFilter() method");
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        try
        {
            if(httpServletRequest.getSession(false)!=null)
            {
                logger.info("doFilter: getSession()=> "+ httpServletRequest.getSession(false).getId());
                SessionHistory sessionHistory=new SessionHistory();
                sessionHistory.setSessionId(httpServletRequest.getSession(false).getId());
                sessionHistory.setLoggedDataTime(LocalDateTime.now());
                sessionHistory.setCreationTime(convertLongDateTime(httpServletRequest.getSession(false).getCreationTime()));
                sessionHistory.setLastAccessTime(convertLongDateTime(httpServletRequest.getSession(false).getLastAccessedTime()));
                sessionHistory.setMaxInactiveInterval(httpServletRequest.getSession(false).getMaxInactiveInterval());

                if(httpServletRequest.getUserPrincipal()!=null)
                    sessionHistory.setUsername(httpServletRequest.getUserPrincipal().getName());
                else
                    sessionHistory.setUsername("");
                try
                {
                    sessionHistoryRepository.saveAndFlush(sessionHistory);
                }
                catch (Exception e)
                {
                    logger.info("Exception occurred");
                }
            }
            else
                logger.info("doFilter: getSession()=> "+null);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy()
    {
        logger.info("Inside CustomFilter destroy() method");

    }


    private LocalDateTime convertLongDateTime(long longValue)
    {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
    }
}
