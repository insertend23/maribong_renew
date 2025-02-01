package com.navangs.maribong.repository.quiz;

import com.navangs.maribong.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findQuizById(Long id);
}
