package com.navangs.maribong.repository.post;

import com.navangs.maribong.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Long countByUserId(String userId);

    Page<Post> findBy(Pageable pageable);
}
