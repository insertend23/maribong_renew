package com.navangs.maribong.dto.post;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyDTO {
    Long communityNo;
    Long replyNo;
    String auserId;
    String content;
    LocalDate regDate;
}
