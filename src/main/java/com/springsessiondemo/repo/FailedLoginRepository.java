package com.springsessiondemo.repo;

import com.springsessiondemo.model.FailedLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedLoginRepository extends JpaRepository<FailedLogin,Long>
{
}
