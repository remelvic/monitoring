package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.MonitoringResult;

import java.util.List;

public interface MonitoringResultService {

    List<MonitoringResult> getLast10Results(Long endpointId, String token);
    void monitorEndpoints();
}
