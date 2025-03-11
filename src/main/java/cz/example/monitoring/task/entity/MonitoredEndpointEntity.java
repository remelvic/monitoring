package cz.example.monitoring.task.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "MonitoredEndpoint")
@Data
public class MonitoredEndpointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String url;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastCheck;
    private Integer monitoredInterval;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity owner;
}

