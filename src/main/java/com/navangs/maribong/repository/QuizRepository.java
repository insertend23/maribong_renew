package com.navangs.maribong.repository;

import com.navangs.maribong.dao.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findQuizById(Integer id);
}
