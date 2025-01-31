package com.navangs.maribong.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizDTO {
    private Long id;
    private String title;
}
