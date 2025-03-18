package cz.example.monitoring.task.mapper;

import cz.example.monitoring.task.data.MonitoredEndpoint;
import cz.example.monitoring.task.data.User;
import cz.example.monitoring.task.entity.MonitoredEndpointEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MonitoredEndpointMapper {

    public static MonitoredEndpoint toDto(MonitoredEndpointEntity entity) {
        if (entity == null) {
            return null;
        }
        MonitoredEndpoint dto = new MonitoredEndpoint();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUrl(entity.getUrl());
        dto.setDateOfCreation(entity.getDateOfCreation());
        dto.setDateOfLastCheck(entity.getDateOfLastCheck());
        dto.setMonitoredInterval(entity.getMonitoredInterval());
        dto.setOwner(new User(entity.getOwner()));
        return dto;
    }

    public static MonitoredEndpointEntity toEntity(MonitoredEndpoint dto) {
        if (dto == null) {
            return null;
        }
        MonitoredEndpointEntity entity = new MonitoredEndpointEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setDateOfCreation(dto.getDateOfCreation());
        entity.setDateOfLastCheck(dto.getDateOfLastCheck());
        entity.setMonitoredInterval(dto.getMonitoredInterval());
//        entity.setOwner(UserMapper.toEntity(dto.getOwner()));
        return entity;
    }

    public static List<MonitoredEndpoint> toDtoList(List<MonitoredEndpointEntity> entities) {
        return entities.stream().map(MonitoredEndpointMapper::toDto).collect(Collectors.toList());
    }

    public static List<MonitoredEndpointEntity> toEntityList(List<MonitoredEndpoint> dtos) {
        return dtos.stream().map(MonitoredEndpointMapper::toEntity).collect(Collectors.toList());
    }
}
