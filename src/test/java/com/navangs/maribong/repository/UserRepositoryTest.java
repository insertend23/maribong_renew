package com.navangs.maribong.repository;

import com.navangs.maribong.dao.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void countBy() {
        Long count = userRepository.countBy();

        Assertions.assertThat(count).isGreaterThan(0);
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
}