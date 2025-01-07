package com.navangs.maribong.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String pwd;
    private String name;
    private String token;
    private String profile;
    private String sex;
    private String birthYear;
    private String birthMonth;
    private String homeCategory;
    private String city1;
    private String city2;
    private String pushChk;
    private LocalDateTime regDate;
    @UpdateTimestamp
    private LocalDateTime modDate;
}
