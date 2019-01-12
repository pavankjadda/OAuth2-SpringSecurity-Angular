package com.springtesting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile
{


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "First name must not be null")
    @Column(name = "first_name", nullable = false)
    private String firstName;


    @NotNull(message = "Last name must not be null")
    @Column(name = "last_name", nullable = false)
    private String lastName;


    @NotNull(message = "Email must not be null")
    @Column(name = "email", nullable = false)
    private String email;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
