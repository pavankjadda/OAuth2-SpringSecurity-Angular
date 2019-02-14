package com.springsessiondemo.repo;

import com.springsessiondemo.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>
{
}
