package com.navangs.maribong.repository.user;

import com.navangs.maribong.entity.user.Notification;
import com.navangs.maribong.entity.user.NotificationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, NotificationId> {
}
