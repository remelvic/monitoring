package cz.example.monitoring.task.data;

import cz.example.monitoring.task.entity.UserEntity;
import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String email;
    private String accessToken;

    public User(UserEntity user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.accessToken = user.getAccessToken();
    }
}
