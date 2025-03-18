package cz.example.monitoring.task.repository;

import cz.example.monitoring.task.entity.MonitoredEndpointEntity;
import cz.example.monitoring.task.entity.MonitoringResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitoringResultRepository extends JpaRepository<MonitoringResultEntity, Long> {

    List<MonitoringResultEntity> findTop10ByMonitoredEndpointIdOrderByDateOfCheckDesc(MonitoredEndpointEntity monitoredEndpointId);

}
