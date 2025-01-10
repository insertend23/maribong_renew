package com.navangs.maribong.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    private String pushChk;

    @ColumnDefault("current_timestamp()")
    @Column(nullable = false)
    private LocalDateTime regDate;

    @UpdateTimestamp
    @ColumnDefault("current_timestamp()")
    @Column(nullable = false)
    private LocalDateTime modDate;

    private String token;
    private String profile;
}
