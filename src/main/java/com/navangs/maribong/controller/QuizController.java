package com.navangs.maribong.controller;

import com.navangs.maribong.dto.quiz.QuestionDTO;
import com.navangs.maribong.dto.quiz.QuizAnswerDTO;
import com.navangs.maribong.dto.user.UserIdRequestDTO;
import com.navangs.maribong.response.BaseResponse;
import com.navangs.maribong.response.QuestionResponse;
import com.navangs.maribong.service.QuizService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/quiz")
public class QuizController {
    private static final String NO_ASSIGNED_QUIZ_MSG = "아직 등록된 퀴즈가 없습니다.";
    private static final String QUIZ_PASS_MSG = "퀴즈를 통과하셨습니다.";
    private static final String QUIZ_FAIL_MSG = "통과하지 못하셨습니다.\n다음에 다시 응시해주시기 바랍니다.";
    private final QuizService quizService;

    @RequestMapping(value = "getQuiz", method = {RequestMethod.GET, RequestMethod.POST})
    public QuestionResponse getQuiz(@RequestBody UserIdRequestDTO userIdDTO) {
        Long quizId = quizService.getAssignedQuizId(userIdDTO.getUserId());

        if (quizId != null) {
            List<QuestionDTO> questions = quizService.getQuestions(quizId);

            return QuestionResponse.getSuccessResponse(questions);
        }

        return QuestionResponse.getFailureResponse(NO_ASSIGNED_QUIZ_MSG);
    }

    @PostMapping(value = "sendQuiz")
    public BaseResponse sendQuiz(@RequestBody QuizAnswerDTO quizAnswerDTO) {
        Boolean isPassed = quizService.sendAnswer(quizAnswerDTO);
        if (isPassed) {
            return BaseResponse.builder()
                .result(QUIZ_PASS_MSG)
                .build();
        }

        return BaseResponse.builder()
            .result(QUIZ_FAIL_MSG)
            .build();
    }
}
