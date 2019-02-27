package com.spring.oauthdemo.repo;

import com.spring.oauthdemo.model.FailedLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedLoginRepository extends JpaRepository<FailedLogin,Long>
{
}
