package com.spring.oauthdemo.repo;

import com.spring.oauthdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>
{
}
