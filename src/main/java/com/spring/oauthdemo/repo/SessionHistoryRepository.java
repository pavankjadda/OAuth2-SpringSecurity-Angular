package com.spring.oauthdemo.repo;

import com.spring.oauthdemo.model.SessionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionHistoryRepository extends JpaRepository<SessionHistory,Long>
{
}
