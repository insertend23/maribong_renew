package com.navangs.maribong;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
@Transactional
@AutoConfigureTestDatabase
class MaribongApplicationTests {

    @Test
    void contextLoads() {
    }

}
