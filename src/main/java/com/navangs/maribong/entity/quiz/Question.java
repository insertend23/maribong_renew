package com.navangs.maribong.entity.quiz;

import com.navangs.maribong.entity.TimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
