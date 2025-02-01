package com.navangs.maribong.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCountResponse {
    private Long count;
}
