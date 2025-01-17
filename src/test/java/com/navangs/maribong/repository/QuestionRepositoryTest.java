package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.domain.Question;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void findByQuizIdIn() {
        List<Integer> quizIds = List.of(1, 3);
        List<Question> questions = questionRepository.findByQuizIdIn(quizIds);

        Assertions.assertThat(questions).hasSize(4);
    }
}