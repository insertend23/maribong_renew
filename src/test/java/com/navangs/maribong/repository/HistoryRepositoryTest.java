package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.dao.History;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    void findByUserId() {
        List<History> history = historyRepository.findByUserId("test");

        Assertions.assertThat(history).hasSize(1);
    }

    @Test
    void findByUserIdNotFound() {
        List<History> history = historyRepository.findByUserId("test1");

        Assertions.assertThat(history).hasSize(0);
    }
}