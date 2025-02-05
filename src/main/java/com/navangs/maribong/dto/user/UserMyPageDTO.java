package com.navangs.maribong.dto.user;

import com.navangs.maribong.entity.user.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserMyPageDTO {
    private String userId;
    private String userName;
    private String sex;
    private Integer birthYear;
    private Integer birthMonth;
    private String profile;
    private String pushCheck;
    private Long cmCnt;

    public static UserMyPageDTO fromEntity(User user, Long postCount) {
        String genderLetter = user.getGender().equals('M') ? "남자" : "여자";
        String profileUrlBase = "http://kyugyut.iptime.org:8090/img/profile/";

        return UserMyPageDTO.builder()
            .userId(user.getId())
            .userName(user.getName())
            .sex(genderLetter)
            .birthYear(user.getBirthYear())
            .birthMonth(user.getBirthMonth())
            .profile(profileUrlBase + user.getProfile())
            .pushCheck(user.getPushChk() ? "1" : "0")
            .cmCnt(postCount)
            .build();
    }
}
