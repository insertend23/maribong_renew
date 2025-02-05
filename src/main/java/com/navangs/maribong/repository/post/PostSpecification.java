package com.navangs.maribong.repository.post;

import com.navangs.maribong.entity.post.Post;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {
    public static Specification<Post> getSpec(String excludeUserId, String country, String group, String reaction) {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(notEqualExcludeUserId(excludeUserId, root, criteriaBuilder));
            predicates.add(equalCountry(country, root, criteriaBuilder));
            predicates.add(likeGroupNameOrContent(group, root, criteriaBuilder));
            predicates.add(equalReaction(reaction, root, criteriaBuilder));

            if (query != null && query.getResultType() != Long.class) {
                query.orderBy(criteriaBuilder.desc(root.get("regTimestamp")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public static Predicate notEqualExcludeUserId(String excludeUserId, Root<Post> root,
                                                  CriteriaBuilder criteriaBuilder) {
        return isValueValid(excludeUserId) ? criteriaBuilder.notEqual(root.get("user").get("id"), excludeUserId)
            : criteriaBuilder.conjunction();
    }

    public static Predicate equalCountry(String country, Root<Post> root, CriteriaBuilder criteriaBuilder) {
        return isValueValid(country) ? criteriaBuilder.equal(root.get("country"), country)
            : criteriaBuilder.conjunction();
    }

    public static Predicate likeGroupNameOrContent(String group, Root<Post> root, CriteriaBuilder criteriaBuilder) {
        if (isValueValid(group)) {
            Predicate groupPredicate = criteriaBuilder.like(root.get("groupName"), "%" + group + "%");
            Predicate contentPredicate = criteriaBuilder.like(root.get("content"), "%" + group + "%");

            return criteriaBuilder.or(groupPredicate, contentPredicate);
        }
        return criteriaBuilder.conjunction();
    }

    public static Predicate equalReaction(String reaction, Root<Post> root, CriteriaBuilder criteriaBuilder) {
        return isValueValid(reaction) ? criteriaBuilder.equal(root.get("reaction"), reaction)
            : criteriaBuilder.conjunction();
    }

    private static Boolean isValueValid(String value) {
        return value != null && !value.isEmpty();
    }
}
