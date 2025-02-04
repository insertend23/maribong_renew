package com.navangs.maribong.service;

import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.dto.post.PostWriteDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import com.navangs.maribong.entity.post.Post;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    List<PostDTO> getPosts(PostRequestDTO postRequestDTO);

    List<PostDTO> getMyPosts(String userId);

    void addPost(List<MultipartFile> images, PostWriteDTO postWriteDTO, String reaction);

    void addPostImage(MultipartFile images, Post post);

    List<List<ReplyDTO>> getReplies(List<Integer> postIds);
}
