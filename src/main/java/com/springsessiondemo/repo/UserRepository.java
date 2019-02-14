package com.springsessiondemo.repo;

import com.springsessiondemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);

    @Override
    Page<User> findAll(Pageable pageable);
}
