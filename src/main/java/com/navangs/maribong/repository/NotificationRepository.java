package com.navangs.maribong.repository;

import com.navangs.maribong.domain.Notification;
import com.navangs.maribong.domain.NotificationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, NotificationId> {
}
