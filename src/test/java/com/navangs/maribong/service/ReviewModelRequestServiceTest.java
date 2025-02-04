package com.navangs.maribong.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewModelRequestServiceTest {
    @Autowired
    private ReviewModelRequestService reviewModelRequestService;

    @Test
    void getReaction() {
        String testContent = "열심을 다해 봉사를 했고, 정말 보람찬 봉사였다고 느꼈다.";

        List<String> reactions = reviewModelRequestService.getReaction(testContent);

        Assertions.assertThat(reactions.getFirst()).isEqualTo("보람찬");
    }
}