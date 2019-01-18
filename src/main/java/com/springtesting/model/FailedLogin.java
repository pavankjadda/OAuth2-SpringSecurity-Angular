package com.springtesting.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "failed_login")
public class FailedLogin
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "logged_date_time")
    private LocalDateTime loggedDataTime;

    @Column(name = "requester_ip_address")
    private String requesterIpAddress ;

    @Column(name = "requester_port")
    private int requesterPort ;

    @Column(name = "browser_information")
    private String browserInformation;

    @Column(name = "browser_name")
    private String browserName;

    @Column(name = "auth_type")
    private String authType;


}
