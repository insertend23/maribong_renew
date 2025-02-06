package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyRequestDTO {
    private String userId;
    private Long communityNo;

    public static ReplyRequestDTO fromInsertDTO(ReplyInsertDTO dto) {
        return ReplyRequestDTO.builder()
            .userId(dto.getUserId())
            .communityNo(dto.getCommunityNo())
            .build();
    }
}
