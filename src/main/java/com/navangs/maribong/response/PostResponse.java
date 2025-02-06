package com.navangs.maribong.response;

import com.navangs.maribong.dto.post.PostOverviewDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    PostOverviewDTO sr;
    List<ReplyDTO> resultReplyList;

    public static PostResponse fromDTOs(PostOverviewDTO postOverviewDTO, List<ReplyDTO> replyDTOList) {
        return PostResponse.builder()
            .sr(postOverviewDTO)
            .resultReplyList(replyDTOList)
            .build();
    }
}
