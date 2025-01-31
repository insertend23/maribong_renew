package com.navangs.maribong.dto;

import com.navangs.maribong.domain.Question;
import java.util.ArrayList;
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

    public static QuestionDTO fromEntity(Question question) {
        List<String> choiceContents = List.of(question.getChoice1(), question.getChoice2(),
            question.getChoice3(), question.getChoice4());

        List<Choice> choices = new ArrayList<>();
        for (long i = 0; i < choiceContents.size(); i++) {
            Choice choice = Choice.builder()
                .choiceNo(i + 1)
                .choiceContent(choiceContents.get((int) i))
                .build();
            choices.add(choice);
        }

        return QuestionDTO.builder()
            .quizNo(question.getQuiz().getId())
            .questionNo(question.getId())
            .questionContent(question.getContent())
            .choiceList(choices)
            .build();
    }
}
