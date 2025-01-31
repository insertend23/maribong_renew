package com.navangs.maribong.service.impl;

import com.navangs.maribong.domain.Question;
import com.navangs.maribong.domain.UserQuiz;
import com.navangs.maribong.dto.QuestionDTO;
import com.navangs.maribong.repository.QuestionRepository;
import com.navangs.maribong.repository.QuizRepository;
import com.navangs.maribong.repository.UserQuizRepository;
import com.navangs.maribong.service.QuizService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final UserQuizRepository userQuizRepository;

    @Override
    public Long getAssignedQuizId(String userId) {
        UserQuiz userQuiz = userQuizRepository.findFirstById_UserIdAndPassYn(userId, true);
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
}
