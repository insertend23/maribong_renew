package com.navangs.maribong.dto.post;

import com.navangs.maribong.entity.post.Post;
import com.navangs.maribong.entity.post.PostPhoto;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    private static final String POST_IMG_UPLOAD_PATH = "/uploads/postimg/";
    String userProfile;
    String thumbnail;
    String userId;
    Integer communityNo;
    String content;
    String country;
    String groupName;
    String areaName;
    String reaction;
    String mark;
    String photoPaths;
    Integer photoCount;
    Integer replyCount;
    Integer likeCount;
    LocalDate regDate;
    Boolean likeYn;

    public static PostDTO create(
        Post post, List<PostPhoto> postPhotos, Integer replyCount, Integer likeCounts, Boolean myPostLike) {
        return PostDTO.builder()
            .userProfile(post.getUser().getProfile())
            .thumbnail(POST_IMG_UPLOAD_PATH + postPhotos.getFirst().getImgName())
            .userId(post.getUser().getId())
            .communityNo(post.getId().intValue())
            .content(post.getContent())
            .country(post.getCountry())
            .groupName(post.getGroupName())
            .areaName(post.getAreaName())
            .reaction(post.getReaction())
            .mark(post.getMark() ? "y" : "n")
            .photoCount(postPhotos.size())
            .replyCount(replyCount)
            .likeCount(likeCounts)
            .regDate(post.getRegTimestamp().toLocalDate())
            .likeYn(myPostLike)
            .build();
    }
}
