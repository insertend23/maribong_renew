package com.navangs.maribong.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginDTO {
    private String userId;
    private String userPwd;
    private String token;
}
