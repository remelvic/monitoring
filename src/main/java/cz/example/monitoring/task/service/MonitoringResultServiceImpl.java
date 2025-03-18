package cz.example.monitoring.task.service;

import cz.example.monitoring.task.data.MonitoringResult;
import cz.example.monitoring.task.entity.MonitoredEndpointEntity;
import cz.example.monitoring.task.entity.MonitoringResultEntity;
import cz.example.monitoring.task.entity.UserEntity;
import cz.example.monitoring.task.repository.MonitoredEndpointRepository;
import cz.example.monitoring.task.repository.MonitoringResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoringResultServiceImpl implements MonitoringResultService {

    private final MonitoredEndpointRepository endpointRepository;
    private final MonitoringResultRepository resultRepository;
    private final UserService userService;
    private final WebClient webClient;


    @Override
    @Scheduled(fixedRate = 10000) // 10 sec
    public void monitorEndpoints() {
        List<MonitoredEndpointEntity> endpoints = endpointRepository.findAll();
        for (MonitoredEndpointEntity endpoint : endpoints) {
            webClient.get()
                    .uri(endpoint.getUrl())
                    .retrieve()
                    .toEntity(String.class)
                    .subscribe(response -> {
                        log.info("Endpoint: " + endpoint);
                        saveMonitoringResult(endpoint, response.getStatusCode().value(), response.getBody());
                    }, error -> {
                        saveMonitoringResult(endpoint, 500, "Request Failed");
                    });
        }
    }

    @Override
    public List<MonitoringResult> getLast10Results(Long endpointId, String token) {
        UserEntity user = userService.getUserByAccessToken(token);
        MonitoredEndpointEntity endpoint = endpointRepository.findById(endpointId)
                .filter(e -> e.getOwner().equals(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

        return resultRepository.findTop10ByMonitoredEndpointIdOrderByDateOfCheckDesc(endpoint)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private MonitoringResult mapToDto(MonitoringResultEntity entity) {
        MonitoringResult result = new MonitoringResult();
        result.setId(entity.getId());
        result.setDateOfCheck(entity.getDateOfCheck());
        result.setReturnedPayload(entity.getReturnedPayload());
        result.setReturnedHttpStatusCode(entity.getReturnedHttpStatusCode());
        return result;
    }

    private void saveMonitoringResult(MonitoredEndpointEntity endpoint, int statusCode, String payload) {
        LocalDateTime saveTime = LocalDateTime.now();
        MonitoringResultEntity result = new MonitoringResultEntity();
        result.setDateOfCheck(saveTime);
        result.setReturnedHttpStatusCode(statusCode);
        result.setReturnedPayload(payload);
        result.setMonitoredEndpointId(endpoint);

        resultRepository.save(result);

        endpoint.setDateOfLastCheck(saveTime);
        endpointRepository.save(endpoint);
    }
}
