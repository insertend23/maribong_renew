package com.navangs.maribong.service;

import com.navangs.maribong.dto.post.PostDeleteDTO;
import com.navangs.maribong.dto.post.PostModifyDTO;
import com.navangs.maribong.dto.post.PostOverviewDTO;
import com.navangs.maribong.dto.post.PostPhotoModifyDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.dto.post.PostWriteDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import com.navangs.maribong.dto.post.ReplyInsertDTO;
import com.navangs.maribong.dto.post.ReplyModifyDTO;
import com.navangs.maribong.dto.post.ReplyRequestDTO;
import com.navangs.maribong.entity.post.Post;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    List<PostOverviewDTO> getPosts(PostRequestDTO postRequestDTO);

    List<PostOverviewDTO> getMyPosts(String userId);

    void addPost(List<MultipartFile> images, PostWriteDTO postWriteDTO, String reaction);

    void updatePost(PostModifyDTO postModifyDTO, String reaction);

    void addPostImage(MultipartFile images, Post post);

    void deletePost(PostDeleteDTO postDeleteDTO);

    void updatePostImage(List<MultipartFile> images, PostPhotoModifyDTO postPhotoModifyDTO);

    List<List<ReplyDTO>> getAllReplies(List<Long> postIds);

    List<ReplyDTO> getReplies(ReplyRequestDTO replyRequestDTO);

    List<ReplyDTO> addReply(ReplyInsertDTO replyInsertDTO);

    void updateReply(ReplyModifyDTO replyModifyDTO);
}
