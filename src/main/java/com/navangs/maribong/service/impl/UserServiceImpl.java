package com.navangs.maribong.service.impl;

import com.navangs.maribong.domain.History;
import com.navangs.maribong.domain.Notification;
import com.navangs.maribong.domain.User;
import com.navangs.maribong.dto.HistoryDTO;
import com.navangs.maribong.dto.NotificationDTO;
import com.navangs.maribong.dto.UserDTO;
import com.navangs.maribong.dto.UserMyPageDTO;
import com.navangs.maribong.exception.DuplicatedUserIdException;
import com.navangs.maribong.exception.UserIdNotFoundException;
import com.navangs.maribong.exception.UserPasswordIncorrectException;
import com.navangs.maribong.repository.HistoryRepository;
import com.navangs.maribong.repository.NotificationRepository;
import com.navangs.maribong.repository.PostRepository;
import com.navangs.maribong.repository.UserRepository;
import com.navangs.maribong.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final NotificationRepository notificationRepository;
    private final HistoryRepository historyRepository;

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public User addUser(UserDTO userDTO) {
        if (isUserExist(userDTO.getId())) {
            throw new DuplicatedUserIdException();
        }
        User newUser = User.fromDTO(userDTO);

        return userRepository.save(newUser);
    }

    @Override
    public User login(String userId, String password) {
        User user = validateAndGetUserEntity(userId);
        if (user.getPwd().equals(password)) {
            return user;
        }
        throw new UserPasswordIncorrectException();
    }

    @Override
    public UserMyPageDTO getUserInfo(String userId) {
        User user = validateAndGetUserEntity(userId);
        Long postCount = postRepository.countByUserId(userId);

        return UserMyPageDTO.fromEntity(user, postCount);
    }

    @Override
    public String getProfile(String userId) {
        User user = validateAndGetUserEntity(userId);

        return user.getProfile();
    }

    @Override
    public String updateProfile(String userId, String profile) {
        User user = validateAndGetUserEntity(userId);
        user.changeProfile(profile);
        User savedUser = userRepository.save(user);

        return savedUser.getProfile();
    }

    @Override
    public String deleteProfile(String userId) {
        User user = validateAndGetUserEntity(userId);
        user.changeProfile(null);
        User savedUser = userRepository.save(user);

        return savedUser.getProfile();
    }

    @Override
    public User modifyUserInfo(UserDTO userDTO) {
        validateAndGetUserEntity(userDTO.getId());
        User updateUser = User.fromDTO(userDTO);

        return userRepository.save(updateUser);
    }

    @Override
    public Boolean switchPushChk(String userId) {
        User savedUser = validateAndGetUserEntity(userId);
        savedUser.conversePushChk();
        User updatedUser = userRepository.save(savedUser);

        return updatedUser.getPushChk();
    }

    @Override
    public List<NotificationDTO> getNotification(String userId) {
        validateAndGetUserEntity(userId);
        List<Notification> notifications = notificationRepository.findAll();

        return notifications.stream()
            .map(NotificationDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<HistoryDTO> getHistory(String userId) {
        validateAndGetUserEntity(userId);
        List<History> historyList = historyRepository.findByUserId(userId);

        return historyList.stream().map(HistoryDTO::fromEntity).toList();
    }

    private Boolean isUserExist(String userId) {
        return userRepository.existsById(userId);
    }

    private User validateAndGetUserEntity(String userId) {
        User savedUser = userRepository.findById(userId).orElse(null);
        if (savedUser == null) {
            throw new UserIdNotFoundException();
        }
        return savedUser;
    }
}
