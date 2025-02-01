package com.navangs.maribong.service;

import com.navangs.maribong.dto.quiz.QuestionDTO;
import com.navangs.maribong.dto.quiz.QuizAnswerDTO;
import java.util.List;

public interface QuizService {
    Long getAssignedQuizId(String userId);

    List<QuestionDTO> getQuestions(Long quizId);

    Boolean sendAnswer(QuizAnswerDTO quizAnswerDTO);
}
