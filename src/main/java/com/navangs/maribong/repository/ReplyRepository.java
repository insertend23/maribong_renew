package com.navangs.maribong.repository;

import com.navangs.maribong.domain.Reply;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findByPost_Id(Integer postId);

    List<Reply> findByPost_Id(Integer postId, Limit limit);
}
