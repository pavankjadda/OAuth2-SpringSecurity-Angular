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
@Table(name = "failed_login")
public class FailedLogin implements Serializable
{

    private static final long serialVersionUID = -6573991828685645990L;
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
