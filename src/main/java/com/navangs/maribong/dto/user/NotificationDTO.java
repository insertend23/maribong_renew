package com.navangs.maribong.dto.user;

import com.navangs.maribong.entity.user.Notification;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDTO {
    Integer category;
    String title1;
    String title2;
    String userId;
    Long communityNo;

    public static NotificationDTO fromEntity(Notification notification) {
        return NotificationDTO.builder()
            .category(notification.getCategory())
            .title1(notification.getUserName())
            .title2(notification.getId().getMessage())
            .userId(notification.getId().getUserId())
            .communityNo(notification.getId().getPostId())
            .build();
    }
}
