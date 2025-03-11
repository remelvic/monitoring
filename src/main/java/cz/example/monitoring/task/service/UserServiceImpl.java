package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.User;
import cz.example.monitoring.task.entity.UserEntity;
import cz.example.monitoring.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> users = repository.findAll();
        log.info(this.getClass().getName() + " --> All users was returned");
        return users.stream().map(User::new).collect(Collectors.toList());
//        return users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    // todo: change this to getUserByToken
    public User getUserById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        if (user.isEmpty()) {
            log.info(this.getClass().getName() + " --> The user was not found");
            return
        } else {
            log.info(this.getClass().getName() + " --> The user was found");
            // todo: change request code
            return
        }
    }

    @Override
    public User createUser(User userDTO) {
        Optional<UserEntity> existingUser = repository
                .findByUserInfo(userDTO.getUsername(), userDTO.getEmail(), userDTO.getAccessToken());

        if (existingUser.isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setAccessToken(UUID.randomUUID());

            UserEntity savedUser = repository.save(user);
            log.info(this.getClass().getName() + " --> User {} was created", userDTO.getUsername());
            return new User(savedUser);

        } else {
            // todo: change request code
            log.info(this.getClass().getName() + " --> User {} already exists", userDTO.getUsername());
            return
        }
    }

    @Override
    public User updateUserById(Long id, User user) {
        UserEntity existingUser = repository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());

            UserEntity updateUser = repository.save(existingUser);
            log.info(this.getClass().getName() + " --> User {} was update", id);
            return new User(updateUser);
        } else {
            log.info(this.getClass().getName() + " --> User {} doesn't exists", id);
            // todo: change request code
            return
        }
    }

    @Override
    // todo: change request code
    public void deleteUserById(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            log.info(this.getClass().getName() + " --> User {} doesn't exists", id);
        }

    }
}
