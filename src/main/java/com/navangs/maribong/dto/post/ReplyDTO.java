package com.navangs.maribong.dto.post;

import com.navangs.maribong.entity.post.Reply;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyDTO {
    private Long communityNo;
    private Long replyNo;
    private String auserId;
    private String content;
    private LocalDate regDate;

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
