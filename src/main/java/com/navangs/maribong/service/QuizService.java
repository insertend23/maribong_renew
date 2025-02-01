package com.navangs.maribong.service;

import com.navangs.maribong.dto.QuestionDTO;
import com.navangs.maribong.dto.QuizAnswerDTO;
import java.util.List;

public interface QuizService {
    Long getAssignedQuizId(String userId);

    List<QuestionDTO> getQuestions(Long quizId);

    Boolean sendAnswer(QuizAnswerDTO quizAnswerDTO);
}
