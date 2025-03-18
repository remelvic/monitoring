package cz.example.monitoring.task.controller;

import cz.example.monitoring.task.data.MonitoredEndpoint;
import cz.example.monitoring.task.service.MonitoredEndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endpoints")
@RequiredArgsConstructor
public class MonitoredEndpointController {

    private final MonitoredEndpointService monitoredEndpointService;

    @GetMapping
    public ResponseEntity<List<MonitoredEndpoint>> getAllEndpoints(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(monitoredEndpointService.getAllEndpoints(token));
    }


    @PostMapping
    public ResponseEntity<MonitoredEndpoint> createEndpoint(@RequestHeader("Authorization") String token, @RequestBody MonitoredEndpoint endpoint) {
        return ResponseEntity.ok(monitoredEndpointService.createEndpoint(token, endpoint));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonitoredEndpoint> updateEndpoint(@PathVariable Long id, @RequestHeader("Authorization") String token, @RequestBody MonitoredEndpoint updatedEndpoint) {
        return ResponseEntity.ok(monitoredEndpointService.updateEndpoint(token, id, updatedEndpoint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndpoint(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        monitoredEndpointService.deleteEndpoint(id, token);
        return ResponseEntity.noContent().build();
    }
}
