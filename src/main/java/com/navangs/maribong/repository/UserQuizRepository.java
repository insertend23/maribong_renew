package com.navangs.maribong.repository;

import com.navangs.maribong.dao.UserQuiz;
import com.navangs.maribong.dao.UserQuizId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizRepository extends JpaRepository<UserQuiz, UserQuizId> {
    List<UserQuiz> findByUserId(String userId);
}
