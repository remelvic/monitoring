package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.MonitoredEndpoint;
import cz.example.monitoring.task.entity.MonitoredEndpointEntity;
import cz.example.monitoring.task.entity.UserEntity;
import cz.example.monitoring.task.mapper.MonitoredEndpointMapper;
import cz.example.monitoring.task.repository.MonitoredEndpointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitoredEndpointServiceImpl implements MonitoredEndpointService {

    private final MonitoredEndpointRepository monitoredEndpointRepository;
    private final UserService userService;

    @Override
    public List<MonitoredEndpoint> getAllEndpoints(String token) {
        UserEntity user = userService.getUserByAccessToken(token);
        if (user != null) {
            return MonitoredEndpointMapper.toDtoList(monitoredEndpointRepository.findByOwner(user).orElse(Collections.emptyList()));
        } else {
            // log
            return Collections.emptyList();
        }
    }

    @Override
    public MonitoredEndpoint createEndpoint(String token, MonitoredEndpoint endpoint) {
        UserEntity user = userService.getUserByAccessToken(token);

        if (user == null) {
            //change response status to 401
            throw new RuntimeException("Unauthorized");
        }
        MonitoredEndpointEntity entity = new MonitoredEndpointEntity();
        entity.setName(endpoint.getName());
        entity.setUrl(endpoint.getUrl());
        entity.setDateOfCreation(LocalDateTime.now());
        entity.setMonitoredInterval(endpoint.getMonitoredInterval());
        entity.setOwner(user);

        MonitoredEndpointEntity savedEntity = monitoredEndpointRepository.save(entity);
        return MonitoredEndpointMapper.toDto(savedEntity);

    }

    @Override
    public MonitoredEndpoint updateEndpoint(String token, Long id, MonitoredEndpoint endpointDTO) {
        UserEntity user = userService.getUserByAccessToken(token);
        MonitoredEndpointEntity endpoint = monitoredEndpointRepository.findById(id)
                .filter(e -> e.getOwner().equals(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

        endpoint.setName(endpointDTO.getName());
        endpoint.setUrl(endpointDTO.getUrl());
        endpoint.setMonitoredInterval(endpointDTO.getMonitoredInterval());
        monitoredEndpointRepository.save(endpoint);
        return MonitoredEndpointMapper.toDto(endpoint);
    }

    @Override
    public void deleteEndpoint(Long id, String token) {
        UserEntity user = userService.getUserByAccessToken(token);
        MonitoredEndpointEntity endpoint = monitoredEndpointRepository.findById(id)
                .filter(e -> e.getOwner().equals(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));
        monitoredEndpointRepository.delete(endpoint);
    }
}
