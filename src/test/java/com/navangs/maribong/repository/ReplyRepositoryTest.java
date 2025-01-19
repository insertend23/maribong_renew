package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.domain.Reply;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;

@DataJpaCustomTest
class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void findByPostId() {
        List<Reply> replies = replyRepository.findByPost_Id(1L);

        Assertions.assertThat(replies).hasSize(5);
    }

    @Test
    void findByPostIdLimit() {
        List<Reply> replies = replyRepository.findByPost_Id(1L, Limit.of(3));

        Assertions.assertThat(replies).hasSize(3);
    }
}