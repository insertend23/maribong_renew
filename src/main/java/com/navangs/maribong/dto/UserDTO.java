package com.navangs.maribong.dto;

import com.navangs.maribong.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;

    private String pwd;

    private String name;

    private Character gender;

    private Integer birthYear;

    private Integer birthMonth;

    private Boolean pushChk;

    private String token;

    private String profile;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
            .id(user.getId())
            .pwd(user.getPwd())
            .name(user.getName())
            .gender(user.getGender())
            .birthYear(user.getBirthYear())
            .birthMonth(user.getBirthMonth())
            .pushChk(user.getPushChk())
            .token(user.getToken())
            .profile(user.getProfile())
            .build();
    }
}
