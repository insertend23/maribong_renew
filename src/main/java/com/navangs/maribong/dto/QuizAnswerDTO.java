package com.navangs.maribong.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizAnswerDTO {
    String quizNo;
    String userId;
    List<String> answer;
}
