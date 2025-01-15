package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.dao.Quiz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class QuizRepositoryTest {
    @Autowired
    private QuizRepository quizRepository;

    @Test
    void findQuizById() {
        Quiz quiz = quizRepository.findQuizById(1);

        Assertions.assertThat(quiz).isNotNull();
    }
}