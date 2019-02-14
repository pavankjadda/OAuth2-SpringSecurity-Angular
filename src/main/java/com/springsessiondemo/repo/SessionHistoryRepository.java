package com.springsessiondemo.repo;

import com.springsessiondemo.model.SessionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionHistoryRepository extends JpaRepository<SessionHistory,Long>
{
}
