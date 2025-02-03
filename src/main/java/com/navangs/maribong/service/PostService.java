package com.navangs.maribong.service;

import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import java.util.List;

public interface PostService {
    List<PostDTO> getPosts(PostRequestDTO postRequestDTO);

    List<List<ReplyDTO>> getReplies(List<Long> postIds);
}
