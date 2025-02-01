package com.navangs.maribong.entity.user;

import com.navangs.maribong.dto.user.UserDTO;
import com.navangs.maribong.dto.user.UserModifyDTO;
import com.navangs.maribong.dto.user.UserRegisterDTO;
import com.navangs.maribong.entity.TimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "user_info")
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends TimeEntity {
    @Id
    @Column(length = 20)
    private String id;

    @Column(nullable = false, length = 20)
    private String pwd;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Character gender;

    @Column(nullable = false)
    private Integer birthYear;

    @Column(nullable = false)
    private Integer birthMonth;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("1")
    private Boolean pushChk;

    private String token;
    private String profile;

    public static User fromDTO(UserDTO dto) {
        return User.builder()
            .id(dto.getId())
            .pwd(dto.getPwd())
            .name(dto.getName())
            .gender(dto.getGender())
            .birthYear(dto.getBirthYear())
            .birthMonth(dto.getBirthMonth())
            .pushChk(dto.getPushChk())
            .token(dto.getToken())
            .profile(dto.getProfile())
            .build();
    }

    public static User fromRegisterDTO(UserRegisterDTO dto) {
        return User.builder()
            .id(dto.getUserId())
            .pwd(dto.getUserPwd())
            .name(dto.getUserName())
            .gender(dto.getSex().charAt(0))
            .birthYear(dto.getBirthYear())
            .birthMonth(dto.getBirthMonth())
            .token(dto.getToken())
            .build();
    }

    public void changeProfile(String profile) {
        this.profile = profile;
    }

    public void changeUserInfo(UserModifyDTO dto) {
        this.name = dto.getUserName();
        this.pwd = dto.getUserPwd();
        this.birthYear = dto.getBirthYear();
        this.birthMonth = dto.getBirthMonth();
        this.pushChk = dto.isPushChk();
    }

    public void conversePushChk() {
        this.pushChk = !this.pushChk;
    }

    public void updateToken(String token) {
        this.token = token;
    }
}
