package com.navangs.maribong.controller;

import com.navangs.maribong.dto.UserCountDTO;
import com.navangs.maribong.dto.UserRegisterDTO;
import com.navangs.maribong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "getUserCount")
    public UserCountDTO getUserCount() {
        Long count = userService.getUserCount();

        return UserCountDTO.builder()
            .count(count)
            .build();
    }

    @PostMapping(value = "userInsert")
    public void userInsert(UserRegisterDTO userRegisterDTO) {
        userService.addUser(userRegisterDTO);
    }
}
