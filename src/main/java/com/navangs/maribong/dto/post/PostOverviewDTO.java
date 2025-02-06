package com.navangs.maribong.dto.post;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostOverviewDTO {
    String userProfile;
    String thumbnail;
    String userId;
    Long communityNo;
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
    Integer likeYn;

    public static PostOverviewDTO of(
        PostDTO postDTO, PostPhotosDTO postPhotos, Integer replyCount, Integer likeCounts, Boolean myPostLike) {
        return PostOverviewDTO.builder()
            .userProfile(postDTO.getUserProfile())
            .thumbnail(postPhotos.getThumbnail())
            .userId(postDTO.getUserId())
            .communityNo(postDTO.getId())
            .content(postDTO.getContent())
            .country(postDTO.getCountry())
            .groupName(postDTO.getGroupName())
            .areaName(postDTO.getAreaName())
            .reaction(postDTO.getReaction())
            .mark(postDTO.getMark())
            .photoPaths(postPhotos.getPaths())
            .photoCount(postPhotos.getCount())
            .replyCount(replyCount)
            .likeCount(likeCounts)
            .regDate(postDTO.getRegDate())
            .likeYn(myPostLike ? 1 : 0)
            .build();
    }
}
