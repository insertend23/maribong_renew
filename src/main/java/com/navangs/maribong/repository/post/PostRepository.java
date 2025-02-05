package com.navangs.maribong.repository.post;

import com.navangs.maribong.entity.post.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Long countByUserId(String userId);

    Page<Post> findBy(Pageable pageable);

    List<Post> findByUser_IdOrderByRegTimestampDesc(String userId);

    List<Post> findByUser_IdNotOrderByRegTimestampDesc(String userId);

    @Query(value = "SELECT p  FROM Post p "
        + "WHERE p.user.id != :excludeUserId "
        + "   AND p.country = :country "
        + "   AND (p.groupName LIKE CONCAT('%', :group, '%') OR p.content LIKE CONCAT('%', :group, '%'))"
        + "   AND p.reaction = :reaction "
        + "ORDER BY p.regTimestamp DESC")
    List<Post> findBySearchOptions(String excludeUserId, String country, String group, String reaction);
}
