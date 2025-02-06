package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyInsertDTO {
    private String userId;
    private Long communityNo;
    private String auserId;
    private String content;
}
