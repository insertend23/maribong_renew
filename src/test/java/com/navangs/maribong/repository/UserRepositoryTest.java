package com.navangs.maribong.repository;

import com.navangs.maribong.DataJpaCustomTest;
import com.navangs.maribong.domain.User;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaCustomTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void count() {
        Long count = userRepository.count();

        Assertions.assertThat(count).isEqualTo(4);
    }

    @Test
    void existsById() {
        Boolean exists = userRepository.existsById("test");

        Assertions.assertThat(exists).isTrue();
    }

    @Test
    void existsByIdNotFound() {
        Boolean exists = userRepository.existsById("test1");

        Assertions.assertThat(exists).isFalse();
    }

    @Test
    void existsByName() {
        Boolean exists = userRepository.existsByName("test");

        Assertions.assertThat(exists).isTrue();
    }

    @Test
    void existsByNameNotFound() {
        Boolean exists = userRepository.existsByName("test1");

        Assertions.assertThat(exists).isFalse();
    }

    @Test
    void existsByNameAndIdNot() {
        Boolean exists = userRepository.existsByNameAndIdNot("test", "test1");

        Assertions.assertThat(exists).isTrue();
    }

    @Test
    void existsByNameAndIdNotNotFound() {
        Boolean exists = userRepository.existsByNameAndIdNot("test", "test");

        Assertions.assertThat(exists).isFalse();
    }

    @Test
    void existsByIdAndPwd() {
        Boolean exists = userRepository.existsByIdAndPwd("test", "testtest");

        Assertions.assertThat(exists).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"test1,testtest", "test,testtest1"})
    void existsByIdAndPwdNotFound(String id, String pwd) {
        Boolean exists = userRepository.existsByIdAndPwd(id, pwd);

        Assertions.assertThat(exists).isFalse();
    }

    @Test
    void existsProfileById() {
        Boolean exists = userRepository.existsProfileById("test");

        Assertions.assertThat(exists).isTrue();
    }

    @Test
    void existsProfileByIdNotFound() {
        Boolean exists = userRepository.existsProfileById("test1");

        Assertions.assertThat(exists).isFalse();
    }

    @Test
    void findUserById() {
        User user = userRepository.findUserById("test");

        Assertions.assertThat(user).isNotNull();
    }

    @Test
    void findUserByIdNotFound() {
        User user = userRepository.findUserById("test1");

        Assertions.assertThat(user).isNull();
    }

    @Test
    void userSaveInsert() {
        User user = User.builder()
            .id("test4")
            .pwd("testtest")
            .name("test4")
            .gender('M')
            .token("testToken")
            .birthYear(2024)
            .birthMonth(10)
            .build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser.getId()).isEqualTo("test4");
    }

    @Test
    void userSaveUpdate() {
        User originUser = userRepository.findUserById("test");
        LocalDateTime originModDate = originUser.getModTimestamp();
        User user = User.builder()
            .id(originUser.getId())
            .pwd(originUser.getPwd())
            .name("testt")
            .gender(originUser.getGender())
            .birthYear(originUser.getBirthYear())
            .birthMonth(originUser.getBirthMonth())
            .pushChk(originUser.getPushChk())
            .regTimestamp(originUser.getRegTimestamp())
            .modTimestamp(originModDate)
            .token(originUser.getToken())
            .profile(originUser.getProfile())
            .build();

        userRepository.save(user);
        User updatedUser = userRepository.findUserById("test");

        Assertions.assertThat(updatedUser.getModTimestamp()).isNotEqualTo(originModDate);
    }
}