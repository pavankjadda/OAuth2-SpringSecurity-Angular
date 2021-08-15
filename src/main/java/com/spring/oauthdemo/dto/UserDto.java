package com.spring.oauthdemo.dto;

import com.spring.oauthdemo.model.Role;
import com.spring.oauthdemo.model.UserProfile;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class UserDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private Boolean active;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

    private Boolean accountNonExpired;

    private UserProfile userProfile;

    private Collection<Role> roles;

}
