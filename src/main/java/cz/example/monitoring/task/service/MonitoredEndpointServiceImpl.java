package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.MonitoredEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitoredEndpointServiceImpl implements MonitoredEndpointService {
    @Override
    public List<MonitoredEndpoint> getAllEndpoints() {
        return null;
    }

    @Override
    public MonitoredEndpoint createEndpoint(MonitoredEndpoint endpoint) {
        return null;
    }

    @Override
    public MonitoredEndpoint updateEndpoint(Long id, MonitoredEndpoint endpoint) {
        return null;
    }

    @Override
    public void deleteEndpoint(Long id) {

    }
}
