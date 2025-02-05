package com.navangs.maribong.dto.post;

import com.navangs.maribong.entity.post.Post;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    Long id;
    String userId;
    String userProfile;
    String content;
    String country;
    String groupName;
    String areaName;
    String reaction;
    String mark;
    LocalDate regDate;

    public static PostDTO fromEntity(Post post, String profileQueryPath) {
        return PostDTO.builder()
            .id(post.getId())
            .userId(post.getUser().getId())
            .userProfile(getProfileUri(post, profileQueryPath))
            .content(post.getContent())
            .country(post.getCountry())
            .groupName(post.getGroupName())
            .areaName(post.getAreaName())
            .reaction(post.getReaction())
            .mark(getMarkSymbol(post.getMark()))
            .regDate(post.getRegTimestamp().toLocalDate())
            .build();
    }

    private static String getProfileUri(Post post, String profileUploadPath) {
        return String.join("/", profileUploadPath, post.getUser().getProfile());
    }

    private static String getMarkSymbol(Boolean isMarked) {
        return isMarked ? "y" : null;
    }
}
