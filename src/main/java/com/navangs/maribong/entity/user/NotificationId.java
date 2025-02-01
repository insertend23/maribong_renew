package com.navangs.maribong.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class NotificationId implements java.io.Serializable {
    private static final long serialVersionUID = 5924656947158541212L;
    @Column(name = "message", nullable = false, length = 200)
    private String message;

    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        NotificationId entity = (NotificationId) o;
        return Objects.equals(this.postId, entity.postId) &&
            Objects.equals(this.message, entity.message) &&
            Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, message, userId);
    }

}