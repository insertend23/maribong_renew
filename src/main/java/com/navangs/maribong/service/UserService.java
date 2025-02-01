package com.navangs.maribong.service;

import com.navangs.maribong.dto.user.HistoryDTO;
import com.navangs.maribong.dto.user.NotificationDTO;
import com.navangs.maribong.dto.user.UserLoginDTO;
import com.navangs.maribong.dto.user.UserModifyDTO;
import com.navangs.maribong.dto.user.UserMyPageDTO;
import com.navangs.maribong.dto.user.UserRegisterDTO;
import com.navangs.maribong.entity.user.User;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    Long getUserCount();

    User addUser(UserRegisterDTO userDTO);

    User login(UserLoginDTO userLoginDTO);

    UserMyPageDTO getUserInfo(String userId);

    String getProfile(String userId);

    String updateProfile(String userId, MultipartFile profile);

    void deleteProfile(String userId);

    User modifyUserInfo(UserModifyDTO userDTO);

    Boolean switchPushChk(String userId);

    List<NotificationDTO> getNotification(String userId);

    List<HistoryDTO> getHistory(String userId);
}
