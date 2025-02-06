package com.navangs.maribong.dto.user;

import com.navangs.maribong.entity.user.History;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HistoryDTO {
    private Long id;

    private String userId;

    private String title;

    private String term;

    public static HistoryDTO fromEntity(History history) {
        return HistoryDTO.builder()
            .id(history.getId())
            .userId(history.getUser().getId())
            .title(history.getTitle())
            .term(String.join(" ~ ", history.getStartDate().toString(), history.getEndDate().toString()))
            .build();
    }
}
