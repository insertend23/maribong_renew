package com.navangs.maribong.service.impl;

import com.navangs.maribong.dto.quiz.QuestionDTO;
import com.navangs.maribong.dto.quiz.QuizAnswerDTO;
import com.navangs.maribong.entity.quiz.Question;
import com.navangs.maribong.entity.quiz.UserQuiz;
import com.navangs.maribong.repository.quiz.QuestionRepository;
import com.navangs.maribong.repository.quiz.UserQuizRepository;
import com.navangs.maribong.service.QuizService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuestionRepository questionRepository;
    private final UserQuizRepository userQuizRepository;

    @Override
    public Long getAssignedQuizId(String userId) {
        UserQuiz userQuiz = getFirstUserQuizNotPassed(userId);
        if (userQuiz == null) {
            return null;
        }

        return userQuiz.getQuiz().getId();
    }

    @Override
    public List<QuestionDTO> getQuestions(Long quizId) {
        List<Question> questions = questionRepository.findQuestionsByQuizId(quizId);

        return questions.stream()
            .map(QuestionDTO::fromEntity)
            .toList();
    }

    @Override
    @Transactional
    public Boolean sendAnswer(QuizAnswerDTO quizAnswerDTO) {
        List<Question> questions = questionRepository.findQuestionsByQuizId(quizAnswerDTO.getQuizNo());

        Boolean isPassed = IntStream.range(0, questions.size())
            .allMatch(index -> questions.get(index).getAnswer().equals(quizAnswerDTO.getAnswer().get(index)));

        if (isPassed) {
            UserQuiz userQuiz = getFirstUserQuizNotPassed(quizAnswerDTO.getUserId());
            userQuiz.pass();

            userQuizRepository.save(userQuiz);
        }

        return isPassed;
    }

    private UserQuiz getFirstUserQuizNotPassed(String userId) {
        return userQuizRepository.findFirstById_UserIdAndPassYn(userId, false);
    }
}
