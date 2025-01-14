package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.dao.Quiz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class QuizRepositoryTest {
    @Autowired
    private QuizRepository quizRepository;

    @BeforeAll
    void setUp() {
        Quiz quiz = Quiz.builder()
            .title("테스트 퀴즈")
            .build();

        quizRepository.save(quiz);
    }

    @Test
    void findQuizById() {
        Quiz quiz = quizRepository.findQuizById(1);

        Assertions.assertThat(quiz).isNotNull();
    }
}