package com.navangs.maribong.controller;

import com.navangs.maribong.dto.post.PostDTO;
import com.navangs.maribong.dto.post.PostDeleteDTO;
import com.navangs.maribong.dto.post.PostLikeRequestDTO;
import com.navangs.maribong.dto.post.PostModifyDTO;
import com.navangs.maribong.dto.post.PostPhotoModifyDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.dto.post.PostWriteDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import com.navangs.maribong.dto.post.ReplyDeleteDTO;
import com.navangs.maribong.dto.post.ReplyInsertDTO;
import com.navangs.maribong.dto.post.ReplyModifyDTO;
import com.navangs.maribong.dto.post.ReplyRequestDTO;
import com.navangs.maribong.response.BaseResponse;
import com.navangs.maribong.response.PostResponse;
import com.navangs.maribong.service.PostService;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/community")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @RequestMapping(value = "getCommunityList")
    public List<PostResponse> getPosts(@RequestBody PostRequestDTO postRequestDTO) {
        List<PostDTO> posts = postService.getPosts(postRequestDTO);
        List<Integer> postIds = posts.stream()
            .map(PostDTO::getCommunityNo)
            .toList();
        List<List<ReplyDTO>> replies = postService.getReplies(postIds);

        return IntStream.range(0, posts.size())
            .mapToObj(index -> PostResponse.fromDTOs(posts.get(index), replies.get(index)))
            .toList();
    }

    @RequestMapping(value = "getMyCommunityList")
    public List<PostDTO> getMyPostList(@RequestBody String userId) {
        return postService.getMyPosts(userId);
    }

    @RequestMapping(value = "insertCommunity")
    public BaseResponse addPost(@RequestBody PostWriteDTO postWriteDTO) {
        postService.addPost(postWriteDTO);
        
        return new BaseResponse("success");
    }

    @RequestMapping(value = "updateCommunity")
    public BaseResponse updatePost(@RequestBody PostModifyDTO postModifyDTO) {
        return new BaseResponse("success");
    }

    @RequestMapping(value = "deleteCommunity")
    public BaseResponse deletePost(@RequestBody PostDeleteDTO postDeleteDTO) {
        return new BaseResponse("success");
    }

    @RequestMapping(value = "updateCommunityImg")
    public BaseResponse updatePostPhoto(@RequestPart PostPhotoModifyDTO postPhotoModifyDTO) {
        return new BaseResponse("success");
    }

    @RequestMapping(value = "deleteCommunityImg")
    public BaseResponse deletePostPhoto(@RequestPart String imgPath) {
        return new BaseResponse("success");
    }

    @RequestMapping(value = "getReplyList")
    public List<ReplyDTO> getReplies(@RequestBody ReplyRequestDTO replyRequestDTO) {
        return null;
    }

    @RequestMapping(value = "insertReply")
    public ReplyDTO addReply(@RequestBody ReplyInsertDTO replyInsertDTO) {
        return null;
    }

    @RequestMapping(value = "updateReply")
    public BaseResponse updateReply(@RequestBody ReplyModifyDTO replyModifyDTO) {
        return new BaseResponse("success");
    }

    @RequestMapping(value = "deleteReply")
    public BaseResponse deleteReply(@RequestBody ReplyDeleteDTO replyDeleteDTO) {
        return new BaseResponse("success");
    }

    @RequestMapping(value = "getContect")
    public String getMyPostLikeInPost(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {
        return null;
    }

    @RequestMapping(value = "insertContect")
    public BaseResponse addPostLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {
        return new BaseResponse("success");
    }

    @RequestMapping(value = "deleteContect")
    public BaseResponse deletePostLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {
        return new BaseResponse("success");
    }
}
