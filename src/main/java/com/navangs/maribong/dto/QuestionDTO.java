package com.navangs.maribong.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {
    private Long quizNo;
    private Long questionNo;
    private String questionContent;
    private List<Choice> choiceList;

    @Builder
    static class Choice {
        private Long choiceNo;
        private String choiceContent;
    }
}
