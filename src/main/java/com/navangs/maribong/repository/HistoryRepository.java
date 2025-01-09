package com.navangs.maribong.repository;

import com.navangs.maribong.dao.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
    History findByUserId(String userId);
}
