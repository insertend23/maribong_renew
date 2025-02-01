package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.entity.post.PostLike;
import com.navangs.maribong.repository.post.PostLikeRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class PostLikeRepositoryTest {
    @Autowired
    private PostLikeRepository postLikeRepository;

    @Test
    void findById_PostId() {
        List<PostLike> likes = postLikeRepository.findById_PostId(1L);

        Assertions.assertThat(likes).hasSize(2);
    }
}