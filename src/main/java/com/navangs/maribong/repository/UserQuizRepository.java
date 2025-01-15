package com.navangs.maribong.repository;

import com.navangs.maribong.dao.Quiz;
import com.navangs.maribong.dao.UserQuiz;
import com.navangs.maribong.dao.UserQuizId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserQuizRepository extends JpaRepository<UserQuiz, UserQuizId> {
    List<UserQuiz> findById_UserId(String userId);

    @Query("SELECT uq.quiz FROM UserQuiz uq LEFT JOIN Quiz q ON uq.quiz.id = q.id WHERE uq.user.id = :userId")
    List<Quiz> findQuizzesByUserId(String userId);

    @Query("SELECT uq.quiz From UserQuiz uq INNER JOIN Quiz q ON uq.quiz.id = q.id WHERE uq.user.id = :userId AND uq.passYn= :isPassed")
    List<Quiz> findQuizzesByUserIdAndPassYn(String userId, boolean isPassed);
}
