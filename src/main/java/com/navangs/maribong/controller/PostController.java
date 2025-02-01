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
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/community")
public class PostController {
    @RequestMapping(value = "getCommunityList")
    public List<PostResponse> getPostList(@RequestBody PostRequestDTO postRequestDTO) {
        return null;
    }

    @RequestMapping(value = "getMyCommunityList")
    public List<PostDTO> getMyPostList(@RequestBody String userId) {
        return null;
    }

    @RequestMapping(value = "insertCommunity")
    public BaseResponse addPost(@RequestBody PostWriteDTO postWriteDTO) {
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
