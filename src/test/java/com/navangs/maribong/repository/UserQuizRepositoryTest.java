package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.dao.Quiz;
import com.navangs.maribong.dao.UserQuiz;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class UserQuizRepositoryTest {
    @Autowired
    UserQuizRepository userQuizRepository;
    
    @Test
    void findByUserId() {
        List<UserQuiz> assignedQuizzes = userQuizRepository.findById_UserId("test");

        Assertions.assertThat(assignedQuizzes.size()).isEqualTo(3);
    }

    @Test
    void findQuizByUserId() {
        List<Quiz> quizzes = userQuizRepository.findQuizzesByUserId("test");

        Assertions.assertThat(quizzes.size()).isEqualTo(3);
    }
}