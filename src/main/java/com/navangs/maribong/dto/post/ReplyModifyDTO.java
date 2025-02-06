package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyModifyDTO {
    String userId;
    Long communityNo;
    Long replyNo;
    String auserId;
    String content;
}
