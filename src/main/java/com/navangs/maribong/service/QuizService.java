package com.navangs.maribong.service;

import com.navangs.maribong.dto.QuestionDTO;
import java.util.List;

public interface QuizService {
    Long getAssignedQuizId(String userId);

    List<QuestionDTO> getQuestions(Long quizId);
}
