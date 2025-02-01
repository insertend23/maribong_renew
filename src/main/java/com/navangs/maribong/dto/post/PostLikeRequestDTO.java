package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLikeRequestDTO {
    String userId;
    Long communityNo;
    String auserId;
}
