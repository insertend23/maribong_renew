package com.navangs.maribong.controller;

import com.navangs.maribong.dto.QuestionDTO;
import com.navangs.maribong.dto.QuizAnswerDTO;
import com.navangs.maribong.response.QuestionResponse;
import com.navangs.maribong.service.QuizService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/quiz")
public class QuizController {
    private static final String NO_ASSIGNED_QUIZ_MSG = "아직 등록된 퀴즈가 없습니다.";
    private final QuizService quizService;

    @RequestMapping(value = "getQuiz", method = {RequestMethod.GET, RequestMethod.POST})
    public QuestionResponse getQuiz(String userId) {
        Long quizId = quizService.getAssignedQuizId(userId);

        if (quizId != null) {
            List<QuestionDTO> questions = quizService.getQuestions(quizId);

            return QuestionResponse.getSuccessResponse(questions);
        }

        return QuestionResponse.getFailureResponse(NO_ASSIGNED_QUIZ_MSG);
    }

    @PostMapping(value = "sendQuiz")
    public Map<String, String> sendQuiz(QuizAnswerDTO quizAnswerDTO) {
        return null;
    }
}
