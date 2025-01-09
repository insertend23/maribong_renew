package com.navangs.maribong.repository;

import com.navangs.maribong.dao.History;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    void findByUserId() {
        History history = historyRepository.findByUserId("test");

        Assertions.assertThat(history).isNotNull();
    }

    @Test
    void findByUserIdNotFound() {
        History history = historyRepository.findByUserId("test1");

        Assertions.assertThat(history).isNull();
    }
}