package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.dao.History;
import com.navangs.maribong.dao.User;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void setUp() {
        User user = User.builder()
            .id("test")
            .pwd("testtest")
            .name("test")
            .gender('M')
            .birthYear(2025)
            .birthMonth(1)
            .build();

        User savedUser = userRepository.save(user);

        History history = History.builder()
            .user(savedUser)
            .title("테스트 봉사기록")
            .startDate(LocalDate.of(2024, 11, 1))
            .endDate(LocalDate.of(2024, 12, 1))
            .build();

        historyRepository.save(history);
    }

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