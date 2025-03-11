package cz.example.monitoring.task.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitoredEndpoint {

    private Long Id;
    private String name;
    private String url;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastCheck;
    private Integer monitoredInterval;
    private User owner;
}
