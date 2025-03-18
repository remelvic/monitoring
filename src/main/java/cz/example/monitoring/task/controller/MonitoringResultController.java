package cz.example.monitoring.task.controller;

import cz.example.monitoring.task.data.MonitoringResult;
import cz.example.monitoring.task.service.MonitoringResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class MonitoringResultController {

    private final MonitoringResultService monitoringResultService;

    @GetMapping("/{endpointId}/last10")
    public ResponseEntity<List<MonitoringResult>> getLast10Results(@PathVariable Long endpointId, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(monitoringResultService.getLast10Results(endpointId, token));
    }
}
