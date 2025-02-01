package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.entity.user.Notification;
import com.navangs.maribong.repository.user.NotificationRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class NotificationRepositoryTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void findAll() {
        List<Notification> notifications = notificationRepository.findAll();

        Notification notice = notifications.stream()
            .filter((not) -> not.getCategory().equals(1))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No notification found"));

        Assertions.assertThat(notice.getUserName()).isEqualTo("관리자");
    }
}