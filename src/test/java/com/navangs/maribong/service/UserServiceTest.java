package com.navangs.maribong.service;

import com.navangs.maribong.dto.user.HistoryDTO;
import com.navangs.maribong.dto.user.NotificationDTO;
import com.navangs.maribong.dto.user.UserDTO;
import com.navangs.maribong.dto.user.UserLoginDTO;
import com.navangs.maribong.dto.user.UserModifyDTO;
import com.navangs.maribong.dto.user.UserMyPageDTO;
import com.navangs.maribong.dto.user.UserRegisterDTO;
import com.navangs.maribong.entity.user.History;
import com.navangs.maribong.entity.user.Notification;
import com.navangs.maribong.entity.user.NotificationId;
import com.navangs.maribong.entity.user.User;
import com.navangs.maribong.exception.DuplicatedUserIdException;
import com.navangs.maribong.exception.UserIdNotFoundException;
import com.navangs.maribong.exception.UserPasswordIncorrectException;
import com.navangs.maribong.repository.post.PostRepository;
import com.navangs.maribong.repository.user.HistoryRepository;
import com.navangs.maribong.repository.user.NotificationRepository;
import com.navangs.maribong.repository.user.UserRepository;
import com.navangs.maribong.service.impl.UserServiceImpl;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static UserDTO testUserDTO;
    private static UserRegisterDTO testUserRegisterDTO;
    private static UserMyPageDTO userMyPageDTO;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeAll
    static void setUp() {
        String testId = "test";
        String testPwd = "testtest";
        String testName = "testtest";
        Character testGender = '1';
        Integer testBirthYear = 2000;
        Integer testBirthMonth = 12;
        Boolean testPushChk = true;
        String testToken = "test_token";
        String testProfile = "test_profile";

        testUserDTO = UserDTO.builder()
            .id(testId)
            .pwd(testPwd)
            .name(testName)
            .gender(testGender)
            .birthYear(testBirthYear)
            .birthMonth(testBirthMonth)
            .pushChk(testPushChk)
            .profile(testProfile)
            .build();

        testUserRegisterDTO = UserRegisterDTO.builder()
            .userId(testId)
            .userPwd(testPwd)
            .userName(testName)
            .sex(testGender.toString())
            .birthYear(testBirthYear)
            .birthMonth(testBirthMonth)
            .token(testToken)
            .build();
    }

    @Test
    void getUserCount() {
        Long testCount = 10L;

        Mockito.when(userRepository.count()).thenReturn(10L);

        Assertions.assertThat(userService.getUserCount()).isEqualTo(10L);
    }

    @Test
    void addUser() {
        User user = User.fromRegisterDTO(testUserRegisterDTO);

        Mockito.when(userRepository.existsById(testUserRegisterDTO.getUserId())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        Assertions.assertThat(userService.addUser(testUserRegisterDTO)).isEqualTo(user);
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void addUserExist() {
        Mockito.when(userRepository.existsById(testUserRegisterDTO.getUserId())).thenReturn(true);

        Assertions.assertThatThrownBy(() -> userService.addUser(testUserRegisterDTO))
            .isInstanceOf(DuplicatedUserIdException.class);
    }

    @Test
    void login() {
        User user = User.fromDTO(testUserDTO);
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
            .userId(testUserDTO.getId())
            .userPwd(testUserDTO.getPwd())
            .token("new_token")
            .build();

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));
        User savedUser = userService.login(userLoginDTO);

        Assertions.assertThat(savedUser.getToken()).isEqualTo(userLoginDTO.getToken());
    }

    @Test
    void loginIdNotFound() {
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
            .userId(testUserDTO.getId())
            .userPwd(testUserDTO.getPwd())
            .token("new_token")
            .build();

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.login(userLoginDTO))
            .isInstanceOf(UserIdNotFoundException.class);
    }

    @Test
    void loginPwdIncorrect() {
        User user = User.fromDTO(testUserDTO);
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
            .userId(testUserDTO.getId())
            .userPwd("wrong_password")
            .token("new_token")
            .build();

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));

        Assertions.assertThatThrownBy(() -> userService.login(userLoginDTO))
            .isInstanceOf(UserPasswordIncorrectException.class);
    }

    @Test
    void getUserInfo() {
        User user = User.fromDTO(testUserDTO);
        Long testPostCount = 5L;

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));
        Mockito.when(postRepository.countByUserId(testUserDTO.getId())).thenReturn(testPostCount);
        UserMyPageDTO userMyPageDTO = userService.getUserInfo(testUserDTO.getId());

        Assertions.assertThat(userMyPageDTO.getUserId()).isEqualTo(testUserDTO.getId());
        Assertions.assertThat(userMyPageDTO.getPostCount()).isEqualTo(testPostCount);
    }

    @Test
    void getUserInfoNotFound() {
        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.getUserInfo(testUserDTO.getId()))
            .isInstanceOf(UserIdNotFoundException.class);
    }

    @Test
    void getProfile() {
        User user = User.fromDTO(testUserDTO);

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));

        Assertions.assertThat(userService.getProfile(testUserDTO.getId())).isEqualTo("test_profile");
    }

    @Test
    void getProfileNotFound() {
        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.getProfile(testUserDTO.getId()))
            .isInstanceOf(UserIdNotFoundException.class);
    }

    //    @Test
    //    void updateProfile() {
    //        UserDTO updateProfileUserDTO = UserDTO.builder().build();
    //        BeanUtils.copyProperties(testUserDTO, updateProfileUserDTO);
    //        updateProfileUserDTO.setProfile("test_profile2");
    //        User user = User.fromDTO(testUserDTO);
    //
    //        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));
    //        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(User.fromDTO(updateProfileUserDTO));
    //
    //        Assertions.assertThat(userService.updateProfile(testUserDTO.getId(), "test_profile2"))
    //            .isEqualTo(updateProfileUserDTO.getProfile());
    //        Mockito.verify(userRepository).save(Mockito.any(User.class));
    //    }
    //
    //    @Test
    //    void updateProfileNotFound() {
    //        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.empty());
    //
    //        Assertions.assertThatThrownBy(() -> userService.updateProfile(testUserDTO.getId(), "test_profile"))
    //            .isInstanceOf(UserIdNotFoundException.class);
    //    }

    @Test
    void deleteProfile() {
        UserDTO deleteProfileUserDTO = UserDTO.builder().build();
        BeanUtils.copyProperties(testUserDTO, deleteProfileUserDTO);
        deleteProfileUserDTO.setProfile(null);
        User user = User.fromDTO(testUserDTO);

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(User.fromDTO(deleteProfileUserDTO));

        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void deleteProfileNotFound() {
        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.deleteProfile(testUserDTO.getId()))
            .isInstanceOf(UserIdNotFoundException.class);
    }

    @Test
    void modifyUserInfo() {
        UserModifyDTO modifiedUserDTO = UserModifyDTO.builder()
            .userId(testUserDTO.getId())
            .userName("modifytest")
            .userPwd("testtest2")
            .build();
        User originUser = User.fromDTO(testUserDTO);
        User modifiedUser = User.fromDTO(testUserDTO);
        modifiedUser.changeUserInfo(modifiedUserDTO);

        Mockito.when(userRepository.findById(modifiedUserDTO.getUserId())).thenReturn(Optional.of(originUser));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(modifiedUser);
        User savedUser = userService.modifyUserInfo(modifiedUserDTO);

        Assertions.assertThat(savedUser.getName()).isEqualTo(modifiedUserDTO.getUserName());
        Assertions.assertThat(savedUser.getPwd()).isEqualTo(modifiedUserDTO.getUserPwd());
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void modifyUserInfoNotFound() {
        UserModifyDTO modifiedUserDTO = UserModifyDTO.builder()
            .userId("wrong_id")
            .userName("modifytest")
            .userPwd("testtest2")
            .build();
        Mockito.when(userRepository.findById(modifiedUserDTO.getUserId())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.modifyUserInfo(modifiedUserDTO))
            .isInstanceOf(UserIdNotFoundException.class);
    }

    @Test
    void switchPushChk() {
        User user = User.fromDTO(testUserDTO);
        Boolean originPushChk = user.getPushChk();

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        Assertions.assertThat(userService.switchPushChk(testUserDTO.getId())).isNotEqualTo(originPushChk);
    }

    @Test
    void getNotification() {
        User user = User.fromDTO(testUserDTO);
        NotificationId testNotificationId = NotificationId.builder()
            .userId("admin")
            .postId(15L)
            .message("<공지>")
            .build();
        List<Notification> notifications = List.of(
            Notification.builder()
                .id(testNotificationId)
                .category(1)
                .userName("관리자")
                .build());

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));
        Mockito.when(notificationRepository.findAll()).thenReturn(notifications);
        NotificationDTO notificationDTO = userService.getNotification(testUserDTO.getId()).getFirst();

        Assertions.assertThat(notificationDTO.getUserId()).isEqualTo("admin");
        Assertions.assertThat(notificationDTO.getCategory()).isEqualTo(1);
        Assertions.assertThat(notificationDTO.getPostId()).isEqualTo(15L);
        Assertions.assertThat(notificationDTO.getUserName()).isEqualTo("관리자");
        Assertions.assertThat(notificationDTO.getMessage()).isEqualTo("<공지>");
    }

    @Test
    void getNotificationNotFound() {
        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.getNotification(testUserDTO.getId()))
            .isInstanceOf(UserIdNotFoundException.class);
    }

    @Test
    void getHistory() {
        User user = User.fromDTO(testUserDTO);
        History history = History.builder()
            .id(1L)
            .title("test")
            .user(user)
            .startDate(LocalDate.now())
            .endDate(LocalDate.now())
            .build();

        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.of(user));
        Mockito.when(historyRepository.findByUserId(user.getId())).thenReturn(List.of(history));
        HistoryDTO historyDTO = userService.getHistory(testUserDTO.getId()).getFirst();

        Assertions.assertThat(historyDTO.getId()).isEqualTo(history.getId());
        Assertions.assertThat(historyDTO.getUserId()).isEqualTo(user.getId());
        Assertions.assertThat(historyDTO.getTitle()).isEqualTo(history.getTitle());
        Assertions.assertThat(historyDTO.getStartDate()).isEqualTo(history.getStartDate());
        Assertions.assertThat(historyDTO.getEndDate()).isEqualTo(history.getEndDate());
    }

    @Test
    void getHistoryNotFound() {
        Mockito.when(userRepository.findById(testUserDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.getHistory(testUserDTO.getId()))
            .isInstanceOf(UserIdNotFoundException.class);
    }
}