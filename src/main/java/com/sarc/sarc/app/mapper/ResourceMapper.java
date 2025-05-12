package com.sarc.sarc.app.mapper;

import com.sarc.sarc.domain.Resource;
import java.util.List;
import java.util.Set;

/**
 * Mapper para conversão entre entidade Resource e DTOs
 */
@Mapper(componentModel = "spring")
public interface ResourceMapper {
    
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);
    
    /**
     * Converte entidade Resource para DTO resumido
     */
    ResourceSummaryDTO toSummaryDto(Resource entity);
    
    /**
     * Converte conjunto de entidades Resource para conjunto de DTOs resumidos
     */
    Set<ResourceSummaryDTO> toSummaryDtoSet(Set<Resource> entities);
    
    /**
     * Converte lista de entidades Resource para lista de DTOs resumidos
     */
    List<ResourceSummaryDTO> toSummaryDtoList(List<Resource> entities);
}