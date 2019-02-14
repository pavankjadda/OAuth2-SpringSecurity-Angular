package com.springsessiondemo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "session_history")
public class SessionHistory
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "logged_date_time")
    private LocalDateTime loggedDataTime;

    @Column(name = "session_id")
    private String sessionId ;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "last_access_time")
    private LocalDateTime lastAccessTime;

    @Column(name = "max_inactive_interval")
    private long maxInactiveInterval;



}
