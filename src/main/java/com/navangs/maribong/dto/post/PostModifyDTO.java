package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostModifyDTO {
    String userId;
    Long communityNo;
    String content;
    String country;
    String groupName;
    String areaName;
}
