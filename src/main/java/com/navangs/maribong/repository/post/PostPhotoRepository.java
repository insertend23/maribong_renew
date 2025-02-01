package com.navangs.maribong.repository.post;

import com.navangs.maribong.entity.post.PostPhoto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostPhotoRepository extends JpaRepository<PostPhoto, Integer> {
    List<PostPhoto> findByPostId(Long postId);
}
