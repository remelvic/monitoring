package cz.example.monitoring.task.controller;

import cz.example.monitoring.task.data.MonitoredEndpoint;
import cz.example.monitoring.task.service.MonitoredEndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/endpoints")
@RequiredArgsConstructor
public class MonitoredEndpointController {

    private final MonitoredEndpointService monitoredEndpointService;

    @GetMapping
    public ResponseEntity<List<MonitoredEndpoint>> getAllEndpoints() {
        return ResponseEntity.ok(monitoredEndpointService.getAllEndpoints());
    }

    @PostMapping
    public ResponseEntity<MonitoredEndpoint> createEndpoint(@RequestBody MonitoredEndpoint endpoint) {
        return ResponseEntity.ok(monitoredEndpointService.createEndpoint(endpoint));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonitoredEndpoint> updateEndpoint(@PathVariable Long id, @RequestBody MonitoredEndpoint updatedEndpoint) {
        return ResponseEntity.ok(monitoredEndpointService.updateEndpoint(id, updatedEndpoint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndpoint(@PathVariable Long id) {
        monitoredEndpointService.deleteEndpoint(id);
        return ResponseEntity.noContent().build();
    }
}
