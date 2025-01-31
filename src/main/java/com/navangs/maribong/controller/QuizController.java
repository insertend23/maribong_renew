package com.navangs.maribong.controller;

import com.navangs.maribong.response.QuestionResponse;
import lombok.RequiredArgsConstructor;
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
}
