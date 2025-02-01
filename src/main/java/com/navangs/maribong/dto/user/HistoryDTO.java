package com.navangs.maribong.dto.user;

import com.navangs.maribong.entity.user.History;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HistoryDTO {
    private Long id;

    private String userId;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    public static HistoryDTO fromEntity(History history) {
        return HistoryDTO.builder()
            .id(history.getId())
            .userId(history.getUser().getId())
            .title(history.getTitle())
            .startDate(history.getStartDate())
            .endDate(history.getEndDate())
            .build();
    }
}
