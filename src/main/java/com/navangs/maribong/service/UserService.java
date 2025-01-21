package com.navangs.maribong.service;

import com.navangs.maribong.domain.User;
import com.navangs.maribong.dto.HistoryDTO;
import com.navangs.maribong.dto.NotificationDTO;
import com.navangs.maribong.dto.UserDTO;
import com.navangs.maribong.dto.UserMyPageDTO;
import com.navangs.maribong.dto.UserRegisterDTO;
import java.util.List;

public interface UserService {
    Long getUserCount();

    User addUser(UserRegisterDTO userDTO);

    User login(String userId, String password);

    UserMyPageDTO getUserInfo(String userId);

    String getProfile(String userId);

    String updateProfile(String userId, String profile);

    String deleteProfile(String userId);

    User modifyUserInfo(UserDTO userDTO);

    Boolean switchPushChk(String userId);

    List<NotificationDTO> getNotification(String userId);

    List<HistoryDTO> getHistory(String userId);
}
