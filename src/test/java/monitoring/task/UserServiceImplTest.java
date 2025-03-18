package monitoring.task;

import cz.example.monitoring.task.data.User;
import cz.example.monitoring.task.entity.UserEntity;
import cz.example.monitoring.task.repository.UserRepository;
import cz.example.monitoring.task.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ApplicationTests.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;
    private User userDto;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUser");
        userEntity.setEmail("test@example.com");
        userEntity.setAccessToken(UUID.randomUUID().toString());

        userDto = new User(userEntity);
    }

    @Test
    void getAllUsers_ShouldReturnUserList() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("testUser", users.get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_UserExists_ShouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        User user = userService.getUserById(1L);

        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_UserDoesNotExist_ShouldReturnNull() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User user = userService.getUserById(1L);

        assertNull(user);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void createUser_NewUser_ShouldReturnCreatedUser() {
        when(userRepository.findByUsernameAndEmailAndAccessToken(any(), any(), any())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        User createdUser = userService.createUser(userDto);

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUsername());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void createUser_UserAlreadyExists_ShouldReturnNull() {
        when(userRepository.findByUsernameAndEmailAndAccessToken(any(), any(), any())).thenReturn(Optional.of(userEntity));

        User createdUser = userService.createUser(userDto);

        assertNull(createdUser);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void updateUserById_UserExists_ShouldUpdateAndReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        User updatedUser = userService.updateUserById(1L, userDto);

        assertNotNull(updatedUser);
        assertEquals("testUser", updatedUser.getUsername());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void updateUserById_UserDoesNotExist_ShouldReturnNull() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User updatedUser = userService.updateUserById(1L, userDto);

        assertNull(updatedUser);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void deleteUserById_UserExists_ShouldDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUserById_UserDoesNotExist_ShouldNotDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(false);

        userService.deleteUserById(1L);

        verify(userRepository, never()).deleteById(1L);
    }

    @Test
    void getUserByAccessToken_UserExists_ShouldReturnUser() {
        when(userRepository.findByAccessToken(anyString())).thenReturn(Optional.of(userEntity));

        UserEntity foundUser = userService.getUserByAccessToken(userEntity.getAccessToken());

        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getUsername());
        verify(userRepository, times(1)).findByAccessToken(anyString());
    }

    @Test
    void getUserByAccessToken_UserDoesNotExist_ShouldReturnNull() {
        when(userRepository.findByAccessToken(anyString())).thenReturn(Optional.empty());

        UserEntity foundUser = userService.getUserByAccessToken("invalid-token");

        assertNull(foundUser);
        verify(userRepository, times(1)).findByAccessToken(anyString());
    }
}
