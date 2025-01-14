package com.navangs.maribong.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String birthYear;

    @Column(nullable = false)
    private String birthMonth;

    @ColumnDefault("1")
    private String pushChk;

    @ColumnDefault("current_timestamp()")
    private LocalDateTime regDate;

    @UpdateTimestamp
    @ColumnDefault("current_timestamp()")
    private LocalDateTime modDate;

    private String token;
    private String profile;
}
