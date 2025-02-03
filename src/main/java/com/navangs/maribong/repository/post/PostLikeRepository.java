package com.navangs.maribong.repository.post;

import com.navangs.maribong.entity.post.PostLike;
import com.navangs.maribong.entity.post.PostLikeId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
    List<PostLike> findById_PostId(Long postId);

    Long countById_PostId(Long postId);

    Boolean existsById_PostIdAndId_UserId(Long postId, String userId);
}
