package com.navangs.maribong.dto.post;

import com.navangs.maribong.entity.post.Reply;
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

    public static ReplyDTO fromEntity(Reply reply) {
        return ReplyDTO.builder()
            .communityNo(reply.getPost().getId())
            .replyNo(reply.getId())
            .auserId(reply.getUser().getId())
            .content(reply.getContent())
            .regDate(reply.getRegTimestamp().toLocalDate())
            .build();
    }
}
