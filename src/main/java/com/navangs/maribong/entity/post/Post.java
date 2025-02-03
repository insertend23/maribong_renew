package com.navangs.maribong.entity.post;

import com.navangs.maribong.dto.post.PostWriteDTO;
import com.navangs.maribong.entity.TimeEntity;
import com.navangs.maribong.entity.user.User;
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
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String groupName;

    @Column(nullable = false, length = 100)
    private String areaName;

    @Column(nullable = false, length = 10)
    private String reaction;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("0")
    private Boolean mark;

    public static Post fromWriteDTO(PostWriteDTO postWriteDTO, String reaction) {
        return Post.builder()
            .user(User.builder().id(postWriteDTO.getUserId()).build())
            .content(postWriteDTO.getContent())
            .country(postWriteDTO.getCountry())
            .groupName(postWriteDTO.getGroupName())
            .areaName(postWriteDTO.getAreaName())
            .reaction(reaction)
            .build();
    }
}
