package com.navangs.maribong.entity.post;

import com.navangs.maribong.dto.post.PostLikeRequestDTO;
import com.navangs.maribong.entity.user.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post_like")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {
    @EmbeddedId
    private PostLikeId id;

    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static PostLike fromInsertDTO(PostLikeRequestDTO postLikeRequestDTO) {
        Post post = Post.builder().id(postLikeRequestDTO.getCommunityNo()).build();
        User user = User.builder().id(postLikeRequestDTO.getUserId()).build();

        return PostLike.builder()
            .post(post)
            .user(user)
            .build();
    }
}
