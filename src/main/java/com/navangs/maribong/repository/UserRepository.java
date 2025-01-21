package com.navangs.maribong.repository;

import com.navangs.maribong.domain.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsById(@NonNull String id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, String id);

    boolean existsByIdAndPwd(String id, String pwd);

    boolean existsProfileById(String id);

    User findUserById(String id);
}
