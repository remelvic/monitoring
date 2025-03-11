package cz.example.monitoring.task.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitoringResult {

    private Long id;
    private LocalDateTime dateOfCheck;
    private Integer returnedHttpStatusCode;
    private String returnedPayload;
    private MonitoredEndpoint monitoredEndpointId;
}
