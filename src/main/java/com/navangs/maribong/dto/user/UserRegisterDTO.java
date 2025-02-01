package com.navangs.maribong.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterDTO {
    private String userId;
    private String userPwd;
    private String userName;
    private String token;
    private String sex;
    private Integer birthYear;
    private Integer birthMonth;
}
