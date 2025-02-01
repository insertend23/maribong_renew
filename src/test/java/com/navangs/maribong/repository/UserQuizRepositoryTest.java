package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.entity.quiz.Quiz;
import com.navangs.maribong.entity.quiz.UserQuiz;
import com.navangs.maribong.repository.quiz.UserQuizRepository;
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

        Assertions.assertThat(assignedQuizzes).hasSize(3);
    }

    @Test
    void findByUserIdNotFound() {
        List<UserQuiz> assignedQuizzes = userQuizRepository.findById_UserId("test1");

        Assertions.assertThat(assignedQuizzes).hasSize(0);
    }

    @Test
    void findQuizzesByUserId() {
        List<Quiz> quizzes = userQuizRepository.findQuizzesByUserId("test");

        Assertions.assertThat(quizzes).hasSize(3);
    }

    @Test
    void findQuizzesByUserIdNotFound() {
        List<Quiz> quizzes = userQuizRepository.findQuizzesByUserId("test1");

        Assertions.assertThat(quizzes).hasSize(0);
    }

    @Test
    void findQuizzesByUserIdAndPassYn() {
        List<Quiz> quizzes = userQuizRepository.findQuizzesByUserIdAndPassYn("test", true);

        Assertions.assertThat(quizzes).hasSize(1);
    }

    @Test
    void findQuizzesByUserIdAndPassYnNotFound() {
        List<Quiz> quizzes = userQuizRepository.findQuizzesByUserIdAndPassYn("test1", false);

        Assertions.assertThat(quizzes).hasSize(0);
    }

    @Test
    void findFirstById_UserIdAndPassYn() {
        UserQuiz userQuiz = userQuizRepository.findFirstById_UserIdAndPassYn("test", true);
        Quiz quiz = userQuiz.getQuiz();

        Assertions.assertThat(userQuiz).isNotNull();
        Assertions.assertThat(quiz.getTitle()).isEqualTo("테스트 퀴즈2");
    }

    @Test
    void findFirstById_UserIdAndPassYnNotFound() {
        UserQuiz userQuiz = userQuizRepository.findFirstById_UserIdAndPassYn("test1", false);

        Assertions.assertThat(userQuiz).isNull();
    }
}