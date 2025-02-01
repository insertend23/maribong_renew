package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyInsertDTO {
    String userId;
    String communityNo;
    String auserId;
    String content;
}
