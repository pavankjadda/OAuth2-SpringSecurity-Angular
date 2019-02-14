package com.springsessiondemo.repo;

import com.springsessiondemo.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>
{
}
