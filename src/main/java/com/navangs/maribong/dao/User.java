package com.navangs.maribong.dao;

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

    @Column(unique = true, nullable = false, length = 100)
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
}
