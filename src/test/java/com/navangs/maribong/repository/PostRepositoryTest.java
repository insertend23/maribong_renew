package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.entity.post.Post;
import com.navangs.maribong.repository.post.PostRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaCustomTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void findAll() {
        List<Post> posts = postRepository.findAll();

        Assertions.assertThat(posts).hasSize(15);
    }

    @Test
    void findPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> posts = postRepository.findBy(pageable);

        Assertions.assertThat(posts).hasSize(10);
    }

    @Test
    void findByUser_IdNotOrderByRegTimestampDesc() {
        String excludeUserId = "admin";

        List<Post> posts = postRepository.findByUser_IdNotOrderByRegTimestampDesc(excludeUserId);

        Assertions.assertThat(posts).hasSize(13);
        Assertions.assertThat(posts.stream().anyMatch(post -> post.getUser().getId().equals(excludeUserId))).isFalse();
    }

    @Test
    void findBySearchOptions() {
        String excludeUserId = "admin";
        String searchCountry = "korea";
        String searchGroup = "test3";
        String searchReaction = "라벨 없음";

        List<Post> posts = postRepository.findBySearchOptions(excludeUserId, searchCountry, searchGroup,
            searchReaction);

        Assertions.assertThat(posts).hasSize(2);
    }

    @Test
    void findBySearchOptions_Content() {
        String excludeUserId = "admin";
        String searchCountry = "world";
        String searchGroup = "글2";
        String searchReaction = "라벨 없음";

        List<Post> posts = postRepository.findBySearchOptions(excludeUserId, searchCountry, searchGroup,
            searchReaction);

        Assertions.assertThat(posts).hasSize(2);
    }

    @Test
    void countById() {
        Long postCount = postRepository.countByUserId("test");

        Assertions.assertThat(postCount).isEqualTo(9L);
    }
}