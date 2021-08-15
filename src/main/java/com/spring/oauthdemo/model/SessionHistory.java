package com.spring.oauthdemo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "session_history")
public class SessionHistory implements Serializable
{
    private static final long serialVersionUID = -8004139183349102769L;
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
