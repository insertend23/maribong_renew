package com.navangs.maribong.repository;

import com.navangs.maribong.domain.PostLike;
import com.navangs.maribong.domain.PostLikeId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
    List<PostLike> findById_PostId(Integer postId);
}
