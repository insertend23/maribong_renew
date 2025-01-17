package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.domain.PostLike;
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
        List<PostLike> likes = postLikeRepository.findById_PostId(1);

        Assertions.assertThat(likes).hasSize(2);
    }
}