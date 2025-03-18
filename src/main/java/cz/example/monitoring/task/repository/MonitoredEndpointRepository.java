package cz.example.monitoring.task.repository;

import cz.example.monitoring.task.entity.MonitoredEndpointEntity;
import cz.example.monitoring.task.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonitoredEndpointRepository extends JpaRepository<MonitoredEndpointEntity, Long> {

    Optional<List<MonitoredEndpointEntity>> findByOwner(UserEntity owner);
}
