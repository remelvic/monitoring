package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.MonitoredEndpoint;

import java.util.List;

public interface MonitoredEndpointService {

    List<MonitoredEndpoint> getAllEndpoints(String token);
    MonitoredEndpoint createEndpoint(String token, MonitoredEndpoint endpoint);
    MonitoredEndpoint updateEndpoint(String token, Long id, MonitoredEndpoint endpoint);
    void deleteEndpoint(Long id, String token);

}
