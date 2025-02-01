package com.navangs.maribong.dto.post;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    String userProfile;
    String thumbnail;
    String userId;
    String communityNo;
    String content;
    String country;
    String groupName;
    String areaName;
    String reaction;
    String mark;
    String photoPaths;
    Long photoCount;
    Long replyCount;
    Long likeCount;
    LocalDate regDate;
    Boolean likeYn;
}
