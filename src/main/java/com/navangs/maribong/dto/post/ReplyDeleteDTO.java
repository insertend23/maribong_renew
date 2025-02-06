package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyDeleteDTO {
    private String userId;
    private Long postId;
    private Long replyNo;
    private String auserId;
}
