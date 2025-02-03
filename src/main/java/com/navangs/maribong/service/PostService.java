package com.navangs.maribong.service;

import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import java.util.List;

public interface PostService {
    List<PostDTO> getPosts(PostRequestDTO postRequestDTO);
}
