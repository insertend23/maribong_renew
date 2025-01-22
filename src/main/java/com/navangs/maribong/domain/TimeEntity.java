package com.navangs.maribong.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@MappedSuperclass
public class TimeEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime regTimestamp;

    @UpdateTimestamp
    private LocalDateTime modTimestamp;
}
