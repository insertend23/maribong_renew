package com.navangs.maribong.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Immutable
@Table(name = "vw_notification", schema = "maribong2")
public class Notification {
    @EmbeddedId
    private NotificationId id;

    @Column(name = "category", nullable = false)
    private Integer category;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

}