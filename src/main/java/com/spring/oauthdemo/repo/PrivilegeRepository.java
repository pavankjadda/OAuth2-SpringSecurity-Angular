package com.spring.oauthdemo.repo;

import com.spring.oauthdemo.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>
{
}
