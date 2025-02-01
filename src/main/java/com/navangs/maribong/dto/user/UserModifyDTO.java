package com.navangs.maribong.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModifyDTO {
    String userId;
    String userName;
    String userPwd;
    Integer birthYear;
    Integer birthMonth;
    boolean pushChk;
}
