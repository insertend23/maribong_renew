package com.navangs.maribong.response;

import com.navangs.maribong.dto.quiz.QuestionDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class QuestionResponse extends BaseResponse {
    private String resultMsg;
    private List<QuestionDTO> list;

    public static QuestionResponse getSuccessResponse(List<QuestionDTO> questions) {
        return QuestionResponse.builder()
            .result("y")
            .resultMsg("")
            .list(questions)
            .build();
    }

    public static QuestionResponse getFailureResponse(String resultMessage) {
        return QuestionResponse.builder()
            .result("n")
            .resultMsg(resultMessage)
            .list(new ArrayList<>())
            .build();
    }
}
