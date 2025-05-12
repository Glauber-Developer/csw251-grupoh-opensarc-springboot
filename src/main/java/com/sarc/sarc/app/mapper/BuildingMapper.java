package com.sarc.sarc.app.mapper;

import com.sarc.sarc.domain.Building;
import java.util.List;

/**
 * Mapper para conversão entre entidade Building e DTOs
 */
@Mapper(componentModel = "spring")
public interface BuildingMapper {
    
    BuildingMapper INSTANCE = Mappers.getMapper(BuildingMapper.class);
    
    /**
     * Converte DTO para entidade Building
     */
    Building toEntity(BuildingDTO dto);
    
    /**
     * Converte entidade Building para DTO
     */
    BuildingDTO toDto(Building entity);
    
    /**
     * Converte lista de entidades Building para lista de DTOs
     */
    List<BuildingDTO> toDtoList(List<Building> entities);
    
    /**
     * Converte entidade Building para DTO resumido
     */
    @Mapping(target = "roomCount", expression = "java(entity.getRooms().size())")
    BuildingSummaryDTO toSummaryDto(Building entity);
    
    /**
     * Converte lista de entidades Building para lista de DTOs resumidos
     */
    List<BuildingSummaryDTO> toSummaryDtoList(List<Building> entities);
}