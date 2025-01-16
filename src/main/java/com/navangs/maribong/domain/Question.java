package com.navangs.maribong.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(nullable = false, length = 100)
    private String answer;

    @Column(nullable = false, length = 100)
    private String choice1;

    @Column(nullable = false, length = 100)
    private String choice2;

    @Column(nullable = false, length = 100)
    private String choice3;

    @Column(nullable = false, length = 100)
    private String choice4;

    @ColumnDefault("current_timestamp()")
    private LocalDateTime regTimestamp;

    @UpdateTimestamp
    @ColumnDefault("current_timestamp()")
    private LocalDateTime modTimestamp;
}
