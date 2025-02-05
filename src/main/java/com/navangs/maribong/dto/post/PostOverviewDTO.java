package com.navangs.maribong.dto.post;

import com.navangs.maribong.entity.post.PostPhoto;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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

    public static PostOverviewDTO create(
        PostDTO postDTO, List<PostPhoto> postPhotos, Integer replyCount, Integer likeCounts, Boolean myPostLike) {
        return PostOverviewDTO.builder()
            .userProfile(postDTO.getUserProfile())
            .thumbnail(
                postPhotos.isEmpty() ? ""
                    : "http://kyugyut.iptime.org:8090/img/postimg/" + postPhotos.getFirst().getImgName())
            .userId(postDTO.getUserId())
            .communityNo(postDTO.getId())
            .content(postDTO.getContent())
            .country(postDTO.getCountry())
            .groupName(postDTO.getGroupName())
            .areaName(postDTO.getAreaName())
            .reaction(postDTO.getReaction())
            .mark(postDTO.getMark())
            .photoPaths(getPostImgPaths(postPhotos))
            .photoCount(postPhotos.size())
            .replyCount(replyCount)
            .likeCount(likeCounts)
            .regDate(postDTO.getRegDate())
            .likeYn(myPostLike ? 1 : 0)
            .build();
    }

    public static PostOverviewDTO create2(
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

    private static String getPostImgPaths(List<PostPhoto> postPhotos) {
        return postPhotos.stream()
            .map(PostPhoto::getImgName)
            .map(name -> "http://kyugyut.iptime.org:8090/img/postimg/" + name)
            .collect(Collectors.joining(","));
    }
}
