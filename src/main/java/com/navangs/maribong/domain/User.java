package com.navangs.maribong.domain;

import com.navangs.maribong.dto.UserDTO;
import com.navangs.maribong.dto.UserRegisterDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_info")
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
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

    @ColumnDefault("current_timestamp()")
    private LocalDateTime regTimestamp;

    @UpdateTimestamp
    @ColumnDefault("current_timestamp()")
    private LocalDateTime modTimestamp;

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

    public void conversePushChk() {
        this.pushChk = !this.pushChk;
    }
}
