package com.navangs.maribong.entity.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Embeddable
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQuizId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -4141199540915502932L;
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        UserQuizId entity = (UserQuizId) o;
        return Objects.equals(this.quizId, entity.quizId) && Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, userId);
    }

}