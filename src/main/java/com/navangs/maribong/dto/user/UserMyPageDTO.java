package com.navangs.maribong.dto.user;

import com.navangs.maribong.entity.user.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserMyPageDTO {
    private String userId;
    private String userName;
    private String gender;
    private Integer birthYear;
    private Integer birthMonth;
    private String profile;
    private Boolean pushChk;
    private Long postCount;

    public static UserMyPageDTO fromEntity(User user, Long postCount) {
        String genderLetter = user.getGender().equals('M') ? "남자" : "여자";

        return UserMyPageDTO.builder()
            .userId(user.getId())
            .userName(user.getName())
            .gender(genderLetter)
            .birthYear(user.getBirthYear())
            .birthMonth(user.getBirthMonth())
            .profile(user.getProfile())
            .pushChk(user.getPushChk())
            .postCount(postCount)
            .build();
    }
}
