package com.navangs.maribong.response;

import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    PostDTO sr;
    List<ReplyDTO> resultReplyList;
}
