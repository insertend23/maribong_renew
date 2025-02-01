package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostWriteDTO {
    String userId;
    String content;
    String country;
    String groupName;
    String areaName;
}
