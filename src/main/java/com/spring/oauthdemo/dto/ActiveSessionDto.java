package com.spring.oauthdemo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActiveSessionDto
{
    private String username;
    private Date lastRequest;
    private String sessionId;
    private boolean expired = false;
}
