package com.navangs.maribong.controller;

import com.navangs.maribong.dto.QuizAnswerDTO;
import com.navangs.maribong.response.QuestionResponse;
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
    @RequestMapping(value = "getQuiz", method = {RequestMethod.GET, RequestMethod.POST})
    public QuestionResponse getQuiz(String userId) {
        return null;
    }

    @PostMapping(value = "sendQuiz")
    public Map<String, String> sendQuiz(QuizAnswerDTO quizAnswerDTO) {
        return null;
    }
}
