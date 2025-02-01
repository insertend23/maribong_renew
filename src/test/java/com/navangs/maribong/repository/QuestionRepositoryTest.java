package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.entity.quiz.Question;
import com.navangs.maribong.repository.quiz.QuestionRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void findQuestionsByQuizId() {
        List<Question> questions = questionRepository.findQuestionsByQuizId(1L);

        Assertions.assertThat(questions).hasSize(3);
    }

    @Test
    void findByQuizIdIn() {
        List<Long> quizIds = List.of(1L, 3L);
        List<Question> questions = questionRepository.findByQuizIdIn(quizIds);

        Assertions.assertThat(questions).hasSize(4);
    }
}