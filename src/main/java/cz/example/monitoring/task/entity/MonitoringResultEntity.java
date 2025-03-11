package cz.example.monitoring.task.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "MonitoringResult")
@Data
public class MonitoringResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateOfCheck;
    private Integer returnedHttpStatusCode;
    private String returnedPayload;

    @ManyToOne
    @JoinColumn(name = "monitored_endpoint_id", nullable = false)
    private MonitoredEndpointEntity monitoredEndpointId;
}
