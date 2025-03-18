package cz.example.monitoring.task.repository;

import cz.example.monitoring.task.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndEmailAndAccessToken(String username, String email, String accessToken);
    Optional<UserEntity> findByAccessToken(String accessToken);
}
