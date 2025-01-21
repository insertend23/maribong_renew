package com.navangs.maribong.dto;

import com.navangs.maribong.domain.Notification;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDTO {
    Integer category;
    String userName;
    String message;
    String userId;
    Long postId;

    public static NotificationDTO fromEntity(Notification notification) {
        return NotificationDTO.builder()
            .category(notification.getCategory())
            .userName(notification.getUserName())
            .message(notification.getId().getMessage())
            .userId(notification.getId().getUserId())
            .postId(notification.getId().getPostId())
            .build();
    }
}
