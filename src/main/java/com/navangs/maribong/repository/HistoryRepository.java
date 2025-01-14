package com.navangs.maribong.repository;

import com.navangs.maribong.dao.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    History findByUserId(String userId);
}
