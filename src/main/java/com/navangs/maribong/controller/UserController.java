package com.navangs.maribong.controller;

import com.navangs.maribong.dto.NotificationDTO;
import com.navangs.maribong.dto.UserCountDTO;
import com.navangs.maribong.dto.UserLoginDTO;
import com.navangs.maribong.dto.UserModifyDTO;
import com.navangs.maribong.dto.UserMyPageDTO;
import com.navangs.maribong.dto.UserRegisterDTO;
import com.navangs.maribong.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "getUserCount", method = {RequestMethod.GET, RequestMethod.POST})
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

    @PostMapping(value = "login")
    public void login(UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);
    }

    @RequestMapping(value = "getUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public UserMyPageDTO getUserInfo(String userId) {
        return userService.getUserInfo(userId);
    }

    @PostMapping(value = "userUpdateProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUserProfile(@RequestPart("userId") String userId, @RequestPart("file") MultipartFile file) {
        userService.updateProfile(userId, file);
    }

    @PostMapping(value = "userDeleteProfile")
    public void deleteUserProfile(String userId) {
        userService.deleteProfile(userId);
    }

    @PostMapping(value = "userUpdateInfo")
    public void updateUserInfo(UserModifyDTO userModifyDTO) {
        userService.modifyUserInfo(userModifyDTO);
    }

    @RequestMapping(value = "getAlerm", method = {RequestMethod.GET, RequestMethod.POST})
    public List<NotificationDTO> getAlerm(String userId) {
        return userService.getNotification(userId);
    }
}
