package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.domain.PostPhoto;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class PostPhotoRepositoryTest {
    @Autowired
    private PostPhotoRepository postPhotoRepository;

    @Test
    void findByPostId() {
        List<PostPhoto> photos = postPhotoRepository.findByPostId(1);

        Assertions.assertThat(photos).hasSize(2);
    }
}