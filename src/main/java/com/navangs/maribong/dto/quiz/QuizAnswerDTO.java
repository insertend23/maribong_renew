package com.navangs.maribong.dto.quiz;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizAnswerDTO {
    Long quizNo;
    String userId;
    List<String> answer;
}
