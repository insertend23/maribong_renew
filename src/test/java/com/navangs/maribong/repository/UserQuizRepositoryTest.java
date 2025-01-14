package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.dao.Quiz;
import com.navangs.maribong.dao.User;
import com.navangs.maribong.dao.UserQuiz;
import com.navangs.maribong.dao.UserQuizId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class UserQuizRepositoryTest {
    @Autowired
    UserQuizRepository userQuizRepository;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void setUp() {
        User user = User.builder()
            .id("test")
            .pwd("testtest")
            .name("test")
            .gender('M')
            .birthYear(2025)
            .birthMonth(1)
            .build();

        User savedUser = userRepository.save(user);

        Quiz quiz = Quiz.builder()
            .title("테스트 퀴즈")
            .build();

        Quiz savedQuiz = quizRepository.save(quiz);

        UserQuizId userQuizId = UserQuizId.builder()
            .quizId(savedQuiz.getId())
            .userId(savedUser.getId())
            .build();
        UserQuiz userQuiz = UserQuiz.builder()
            .id(userQuizId)
            .user(savedUser)
            .quiz(savedQuiz)
            .build();

        userQuizRepository.save(userQuiz);
    }

    @Test
    void findQuizByUserId() {
        UserQuiz quiz = userQuizRepository.findByUserId("test").getFirst();

        System.out.println(quiz);
    }
}