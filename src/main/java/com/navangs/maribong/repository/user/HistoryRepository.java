package com.navangs.maribong.repository.user;

import com.navangs.maribong.entity.user.History;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByUserId(String userId);
}
