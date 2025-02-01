package com.navangs.maribong.repository.post;

import com.navangs.maribong.entity.post.Reply;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findByPost_Id(Long postId);

    List<Reply> findByPost_Id(Long postId, Limit limit);
}
