package com.navangs.maribong.controller;

import com.navangs.maribong.dto.user.HistoryDTO;
import com.navangs.maribong.dto.user.NotificationDTO;
import com.navangs.maribong.dto.user.UserIdRequestDTO;
import com.navangs.maribong.dto.user.UserLoginDTO;
import com.navangs.maribong.dto.user.UserModifyDTO;
import com.navangs.maribong.dto.user.UserMyPageDTO;
import com.navangs.maribong.dto.user.UserRegisterDTO;
import com.navangs.maribong.response.BaseResponse;
import com.navangs.maribong.response.UserCountResponse;
import com.navangs.maribong.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private static final BaseResponse SUCCESS_CODE_RESPONSE = new BaseResponse("100");
    private final UserService userService;

    @RequestMapping(value = "getUserCount", method = {RequestMethod.GET, RequestMethod.POST})
    public UserCountResponse getUserCount() {
        Long count = userService.getUserCount();

        return UserCountResponse.builder()
            .count(count)
            .build();
    }

    @PostMapping(value = "userInsert")
    public BaseResponse userInsert(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.addUser(userRegisterDTO);

        return SUCCESS_CODE_RESPONSE;
    }

    @PostMapping(value = "login")
    public BaseResponse login(@RequestBody UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);

        return SUCCESS_CODE_RESPONSE;
    }

    @RequestMapping(value = "getUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public UserMyPageDTO getUserInfo(@RequestBody UserIdRequestDTO userIdDTO) {
        return userService.getUserInfo(userIdDTO.getUserId());
    }

    @PostMapping(value = "userUpdateProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse updateUserProfile(@RequestPart("userId") String userId,
                                          @RequestPart("userProfile") MultipartFile file) {
        String profileUrl = userService.updateProfile(userId, file);

        return new BaseResponse(profileUrl);
    }

    @PostMapping(value = "userDeleteProfile")
    public BaseResponse deleteUserProfile(@RequestBody UserIdRequestDTO userIdDTO) {
        userService.deleteProfile(userIdDTO.getUserId());

        return new BaseResponse("success");
    }

    @PostMapping(value = "userUpdateInfo")
    public BaseResponse updateUserInfo(@RequestBody UserModifyDTO userModifyDTO) {
        userService.modifyUserInfo(userModifyDTO);

        return SUCCESS_CODE_RESPONSE;
    }

    @RequestMapping(value = "getAlerm", method = {RequestMethod.GET, RequestMethod.POST})
    public List<NotificationDTO> getAlerm(@RequestBody UserIdRequestDTO userIdDTO) {
        return userService.getNotification(userIdDTO.getUserId());
    }

    @RequestMapping(value = "getHistory", method = {RequestMethod.GET, RequestMethod.POST})
    public List<HistoryDTO> getHistory(@RequestBody UserIdRequestDTO userIdDTO) {
        return userService.getHistory(userIdDTO.getUserId());
    }
}
