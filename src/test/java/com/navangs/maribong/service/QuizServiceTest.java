package com.navangs.maribong.service;

import com.navangs.maribong.dto.quiz.QuestionDTO;
import com.navangs.maribong.dto.quiz.QuizAnswerDTO;
import com.navangs.maribong.entity.quiz.Question;
import com.navangs.maribong.entity.quiz.Quiz;
import com.navangs.maribong.entity.quiz.UserQuiz;
import com.navangs.maribong.repository.quiz.QuestionRepository;
import com.navangs.maribong.repository.quiz.UserQuizRepository;
import com.navangs.maribong.service.impl.QuizServiceImpl;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private UserQuizRepository userQuizRepository;

    @InjectMocks
    private QuizServiceImpl quizService;

    private static final String TEST_USER_ID = "test";
    private static final String ANSWER_LITERAL = "answer";
    private static final String WRONG_ANSWER_LITERAL = "not answer";
    private static final Long TEST_QUIZ_ID = 1L;
    private static UserQuiz TEST_USER_QUIZ;
    private static Question TEST_QUESTION;

    @BeforeAll
    static void setUp() {
        Quiz testQuiz = Quiz.builder()
            .id(TEST_QUIZ_ID)
            .build();

        TEST_USER_QUIZ = UserQuiz.builder()
            .quiz(testQuiz)
            .build();

        TEST_QUESTION = Question.builder()
            .quiz(testQuiz)
            .answer(ANSWER_LITERAL)
            .choice1(ANSWER_LITERAL)
            .choice2(WRONG_ANSWER_LITERAL)
            .choice3(WRONG_ANSWER_LITERAL)
            .choice4(WRONG_ANSWER_LITERAL)
            .build();
    }

    @Test
    void getAssignedQuizId() {
        Mockito.when(userQuizRepository.findFirstById_UserIdAndPassYn(TEST_USER_ID, false))
            .thenReturn(TEST_USER_QUIZ);

        Assertions.assertThat(quizService.getAssignedQuizId(TEST_USER_ID)).isEqualTo(TEST_QUIZ_ID);
    }

    @Test
    void getAssignedQuizIdNotFound() {
        Mockito.when(userQuizRepository.findFirstById_UserIdAndPassYn(TEST_USER_ID, false))
            .thenReturn(null);

        Assertions.assertThat(quizService.getAssignedQuizId(TEST_USER_ID)).isNull();
    }

    @Test
    void getQuestions() {
        Mockito.when(questionRepository.findQuestionsByQuizId(TEST_QUIZ_ID))
            .thenReturn(List.of(TEST_QUESTION));

        List<QuestionDTO> questions = quizService.getQuestions(TEST_QUIZ_ID);
        QuestionDTO assertQuestion = questions.getFirst();

        Assertions.assertThat(assertQuestion.getQuizNo()).isEqualTo(TEST_QUIZ_ID);
        Assertions.assertThat(assertQuestion.getChoiceList()).hasSize(4);
    }

    @Test
    void sendAnswer() {
        QuizAnswerDTO testAnswerDTO = QuizAnswerDTO.builder()
            .userId(TEST_USER_ID)
            .quizNo(TEST_QUIZ_ID)
            .answer(List.of(ANSWER_LITERAL))
            .build();
        Mockito.when(questionRepository.findQuestionsByQuizId(TEST_QUIZ_ID))
            .thenReturn(List.of(TEST_QUESTION));
        Mockito.when(userQuizRepository.findFirstById_UserIdAndPassYn(TEST_USER_ID, false))
            .thenReturn(TEST_USER_QUIZ);

        Assertions.assertThat(quizService.sendAnswer(testAnswerDTO)).isTrue();
        Mockito.verify(userQuizRepository).save(Mockito.any(UserQuiz.class));
    }

    @Test
    void sendWrongAnswer() {
        QuizAnswerDTO testAnswerDTO = QuizAnswerDTO.builder()
            .userId(TEST_USER_ID)
            .quizNo(TEST_QUIZ_ID)
            .answer(List.of(WRONG_ANSWER_LITERAL))
            .build();
        Mockito.when(questionRepository.findQuestionsByQuizId(TEST_QUIZ_ID))
            .thenReturn(List.of(TEST_QUESTION));

        Assertions.assertThat(quizService.sendAnswer(testAnswerDTO)).isFalse();
    }
}