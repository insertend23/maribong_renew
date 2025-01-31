package com.navangs.maribong.response;

import com.navangs.maribong.dto.QuestionDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponse {
    private String result;
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
