package com.navangs.maribong.service.impl;

import com.navangs.maribong.domain.History;
import com.navangs.maribong.domain.Notification;
import com.navangs.maribong.domain.User;
import com.navangs.maribong.dto.HistoryDTO;
import com.navangs.maribong.dto.NotificationDTO;
import com.navangs.maribong.dto.UserDTO;
import com.navangs.maribong.dto.UserLoginDTO;
import com.navangs.maribong.dto.UserMyPageDTO;
import com.navangs.maribong.dto.UserRegisterDTO;
import com.navangs.maribong.exception.DuplicatedUserIdException;
import com.navangs.maribong.exception.FileTransferFailedException;
import com.navangs.maribong.exception.UserIdNotFoundException;
import com.navangs.maribong.exception.UserPasswordIncorrectException;
import com.navangs.maribong.repository.HistoryRepository;
import com.navangs.maribong.repository.NotificationRepository;
import com.navangs.maribong.repository.PostRepository;
import com.navangs.maribong.repository.UserRepository;
import com.navangs.maribong.service.UserService;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String PROFILE_UPLOAD_PATH = "./src/main/uploads/profile/";
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NotificationRepository notificationRepository;
    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    @Transactional
    public User addUser(UserRegisterDTO userDTO) {
        if (isUserExist(userDTO.getUserId())) {
            throw new DuplicatedUserIdException();
        }
        User newUser = User.fromRegisterDTO(userDTO);

        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public User login(UserLoginDTO userLoginDTO) {
        User user = validateAndGetUserEntity(userLoginDTO.getUserId());
        if (isPasswordIncorrect(user.getPwd(), userLoginDTO.getUserPwd())) {
            throw new UserPasswordIncorrectException();
        }
        user.updateToken(userLoginDTO.getToken());
        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public UserMyPageDTO getUserInfo(String userId) {
        User user = validateAndGetUserEntity(userId);
        Long postCount = postRepository.countByUserId(userId);

        return UserMyPageDTO.fromEntity(user, postCount);
    }

    @Override
    @Transactional
    public String getProfile(String userId) {
        User user = validateAndGetUserEntity(userId);

        return user.getProfile();
    }

    @Override
    @Transactional
    public void updateProfile(String userId, MultipartFile profile) {
        User user = validateAndGetUserEntity(userId);
        String randomProfileName = UUID.randomUUID().toString();
        user.changeProfile(randomProfileName);
        userRepository.save(user);

        uploadFile(profile, randomProfileName);
    }

    @Override
    @Transactional
    public String deleteProfile(String userId) {
        User user = validateAndGetUserEntity(userId);
        user.changeProfile(null);
        User savedUser = userRepository.save(user);

        return savedUser.getProfile();
    }

    @Override
    @Transactional
    public User modifyUserInfo(UserDTO userDTO) {
        validateAndGetUserEntity(userDTO.getId());
        User updateUser = User.fromDTO(userDTO);

        return userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public Boolean switchPushChk(String userId) {
        User savedUser = validateAndGetUserEntity(userId);
        savedUser.conversePushChk();
        User updatedUser = userRepository.save(savedUser);

        return updatedUser.getPushChk();
    }

    @Override
    @Transactional
    public List<NotificationDTO> getNotification(String userId) {
        validateAndGetUserEntity(userId);
        List<Notification> notifications = notificationRepository.findAll();

        return notifications.stream()
            .map(NotificationDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<HistoryDTO> getHistory(String userId) {
        validateAndGetUserEntity(userId);
        List<History> historyList = historyRepository.findByUserId(userId);

        return historyList.stream().map(HistoryDTO::fromEntity).toList();
    }

    private Boolean isUserExist(String userId) {
        return userRepository.existsById(userId);
    }

    private Boolean isPasswordIncorrect(String originPwd, String loginPwd) {
        return !originPwd.equals(loginPwd);
    }

    private User validateAndGetUserEntity(String userId) {
        User savedUser = userRepository.findById(userId).orElse(null);
        if (savedUser == null) {
            throw new UserIdNotFoundException();
        }
        return savedUser;
    }

    private void uploadFile(MultipartFile profile, String randomProfileName) {
        String fileExtension = StringUtils.getFilenameExtension(profile.getOriginalFilename());
        String fileName = randomProfileName + "." + fileExtension;
        Path uploadPath = Path.of(PROFILE_UPLOAD_PATH + fileName).toAbsolutePath();
        try {
            profile.transferTo(uploadPath);
        } catch (IOException e) {
            throw new FileTransferFailedException();
        }
    }
}
