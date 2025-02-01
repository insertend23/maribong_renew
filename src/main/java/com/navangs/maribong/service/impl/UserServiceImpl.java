package com.navangs.maribong.service.impl;

import com.navangs.maribong.dto.user.HistoryDTO;
import com.navangs.maribong.dto.user.NotificationDTO;
import com.navangs.maribong.dto.user.UserLoginDTO;
import com.navangs.maribong.dto.user.UserModifyDTO;
import com.navangs.maribong.dto.user.UserMyPageDTO;
import com.navangs.maribong.dto.user.UserRegisterDTO;
import com.navangs.maribong.entity.user.History;
import com.navangs.maribong.entity.user.Notification;
import com.navangs.maribong.entity.user.User;
import com.navangs.maribong.exception.DuplicatedUserIdException;
import com.navangs.maribong.exception.UserIdNotFoundException;
import com.navangs.maribong.exception.UserPasswordIncorrectException;
import com.navangs.maribong.repository.post.PostRepository;
import com.navangs.maribong.repository.user.HistoryRepository;
import com.navangs.maribong.repository.user.NotificationRepository;
import com.navangs.maribong.repository.user.UserRepository;
import com.navangs.maribong.service.ImageService;
import com.navangs.maribong.service.UserService;
import jakarta.transaction.Transactional;
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
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NotificationRepository notificationRepository;
    private final HistoryRepository historyRepository;
    private final ImageService imageService;

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

        String savedProfileName = user.getProfile();
        imageService.deleteImage(savedProfileName);

        String profileExtension = StringUtils.getFilenameExtension(profile.getOriginalFilename());
        String randomProfileName = String.join(".", UUID.randomUUID().toString(), profileExtension);
        user.changeProfile(randomProfileName);
        userRepository.save(user);
        imageService.uploadImage(profile, randomProfileName);
    }

    @Override
    @Transactional
    public void deleteProfile(String userId) {
        User user = validateAndGetUserEntity(userId);

        String savedProfileName = user.getProfile();
        imageService.deleteImage(savedProfileName);

        user.changeProfile(null);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User modifyUserInfo(UserModifyDTO userDTO) {
        User user = validateAndGetUserEntity(userDTO.getUserId());
        user.changeUserInfo(userDTO);
        return userRepository.save(user);
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
}
