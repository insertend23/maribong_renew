package com.navangs.maribong.controller;

import com.navangs.maribong.dto.post.PostDeleteDTO;
import com.navangs.maribong.dto.post.PostLikeRequestDTO;
import com.navangs.maribong.dto.post.PostModifyDTO;
import com.navangs.maribong.dto.post.PostOverviewDTO;
import com.navangs.maribong.dto.post.PostPhotoModifyDTO;
import com.navangs.maribong.dto.post.PostRequestDTO;
import com.navangs.maribong.dto.post.PostWriteDTO;
import com.navangs.maribong.dto.post.ReplyDTO;
import com.navangs.maribong.dto.post.ReplyDeleteDTO;
import com.navangs.maribong.dto.post.ReplyInsertDTO;
import com.navangs.maribong.dto.post.ReplyModifyDTO;
import com.navangs.maribong.dto.post.ReplyRequestDTO;
import com.navangs.maribong.dto.user.UserIdRequestDTO;
import com.navangs.maribong.response.BaseResponse;
import com.navangs.maribong.response.MyPostLikeResponse;
import com.navangs.maribong.response.PostResponse;
import com.navangs.maribong.service.PostService;
import com.navangs.maribong.service.ReviewModelRequestService;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/community")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ReviewModelRequestService reviewModelRequestService;

    @RequestMapping(value = "getCommunityList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<PostResponse> getPosts(@RequestBody PostRequestDTO postRequestDTO) {
        List<PostOverviewDTO> posts = postService.getPosts(postRequestDTO);
        List<Long> postIds = posts.stream()
            .map(PostOverviewDTO::getCommunityNo)
            .toList();
        List<List<ReplyDTO>> replies = postService.getAllReplies(postIds);

        return IntStream.range(0, posts.size())
            .mapToObj(index -> PostResponse.fromDTOs(posts.get(index), replies.get(index)))
            .toList();
    }

    @RequestMapping(value = "getMyCommunityList", method = {RequestMethod.GET, RequestMethod.POST})
    public List<PostOverviewDTO> getMyPostList(@RequestBody UserIdRequestDTO userIdDTO) {
        return postService.getMyPosts(userIdDTO.getUserId());
    }

    @PostMapping(value = "insertCommunity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse addPost(@RequestPart(name = "picture", required = false) List<MultipartFile> images,
                                @ModelAttribute @ParameterObject PostWriteDTO postWriteDTO) {
        String reaction = getReaction(postWriteDTO.getContent());
        postService.addPost(images, postWriteDTO, reaction);

        return new BaseResponse("success");
    }

    @PostMapping(value = "updateCommunity")
    public BaseResponse updatePost(@RequestBody PostModifyDTO postModifyDTO) {
        String reaction = getReaction(postModifyDTO.getContent());
        postService.updatePost(postModifyDTO, reaction);

        return new BaseResponse("success");
    }

    @PostMapping(value = "deleteCommunity")
    public BaseResponse deletePost(@RequestBody PostDeleteDTO postDeleteDTO) {
        postService.deletePost(postDeleteDTO);

        return new BaseResponse("success");
    }

    @PostMapping(value = "updateCommunityImg")
    public BaseResponse updatePostPhoto(@RequestPart(name = "picture") List<MultipartFile> images,
                                        @RequestPart PostPhotoModifyDTO postPhotoModifyDTO) {
        postService.updatePostImage(images, postPhotoModifyDTO);

        return new BaseResponse("success");
    }

    @PostMapping(value = "getReplyList")
    public List<ReplyDTO> getReplies(@RequestBody ReplyRequestDTO replyRequestDTO) {
        return postService.getReplies(replyRequestDTO);
    }

    @PostMapping(value = "insertReply")
    public List<ReplyDTO> addReply(@RequestBody ReplyInsertDTO replyInsertDTO) {
        return postService.addReply(replyInsertDTO);
    }

    @PostMapping(value = "updateReply")
    public BaseResponse updateReply(@RequestBody ReplyModifyDTO replyModifyDTO) {
        postService.updateReply(replyModifyDTO);

        return new BaseResponse("success");
    }

    @PostMapping(value = "deleteReply")
    public BaseResponse deleteReply(@RequestBody ReplyDeleteDTO replyDeleteDTO) {
        postService.deleteReply(replyDeleteDTO);

        return new BaseResponse("success");
    }

    @PostMapping(value = "getContect")
    public MyPostLikeResponse getMyPostLikeInPost(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {
        String auserId = postService.getMyPostLikeInPost(postLikeRequestDTO);
        
        return MyPostLikeResponse.builder()
            .auserId(auserId)
            .build();
    }

    @PostMapping(value = "insertContect")
    public BaseResponse addPostLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {
        return new BaseResponse("success");
    }

    @PostMapping(value = "deleteContect")
    public BaseResponse deletePostLike(@RequestBody PostLikeRequestDTO postLikeRequestDTO) {
        return new BaseResponse("success");
    }

    private String getReaction(String content) {
        return String.join(", ", reviewModelRequestService.getReaction(content));
    }
}
