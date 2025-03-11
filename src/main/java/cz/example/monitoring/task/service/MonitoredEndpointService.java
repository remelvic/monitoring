package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.MonitoredEndpoint;

import java.util.List;

public interface MonitoredEndpointService {

    List<MonitoredEndpoint> getAllEndpoints();
    MonitoredEndpoint createEndpoint(MonitoredEndpoint endpoint);
    MonitoredEndpoint updateEndpoint(Long id, MonitoredEndpoint endpoint);
    void deleteEndpoint(Long id);

}
