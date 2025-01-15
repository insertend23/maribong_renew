package com.navangs.maribong.repository;

import com.navangs.maribong.dao.History;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByUserId(String userId);
}
