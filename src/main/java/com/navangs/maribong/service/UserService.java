package com.navangs.maribong.service;

import com.navangs.maribong.domain.History;
import com.navangs.maribong.dto.NotificationDTO;
import com.navangs.maribong.dto.UserDTO;
import com.navangs.maribong.dto.UserMyPageDTO;

public interface UserService {
    Long getUserCount();

    void addUser(UserDTO user);

    void login(String userId, String password);

    UserMyPageDTO getUserInfo(String userId);

    void updateProfile(String userId, String profile);

    void deleteProfile(String userId);

    void modifyUserInfo(UserDTO user);

    void switchPushChk(String userId);

    NotificationDTO getNotification(String userId);

    History getHistory(String userId);
}
