package com.navangs.maribong.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestDTO {
    String userId;
    String country;
    String group;
    String reaction;

    public Boolean hasNoSearchOptions() {
        return this.country.isEmpty() && this.group.isEmpty() && this.reaction.isEmpty();
    }
}
