package com.navangs.maribong.response;

import com.navangs.maribong.dto.QuestionDTO;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponse {
    String result;
    String resultMsg;
    List<QuestionDTO> list;
}
