package com.sarc.sarc.app.mapper;

import com.sarc.sarc.domain.Building;
import com.sarc.sarc.domain.Resource;
import com.sarc.sarc.domain.Room;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper para conversão entre entidade Room e DTOs
 */
@Mapper(componentModel = "spring", uses = {BuildingMapper.class, ResourceMapper.class})
public interface RoomMapper {
    
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    
    /**
     * Converte DTO para entidade Room
     */
    @Mapping(target = "building", ignore = true)
    @Mapping(target = "resources", ignore = true)
    Room toEntity(RoomDTO dto);
    
    /**
     * Converte entidade Room para DTO
     */
    @Mapping(target = "buildingId", source = "building.id")
    @Mapping(target = "resourceIds", source = "resources", qualifiedByName = "resourcesToIds")
    RoomDTO toDto(Room entity);
    
    /**
     * Converte lista de entidades Room para lista de DTOs
     */
    List<RoomDTO> toDtoList(List<Room> entities);
    
    /**
     * Converte entidade Room para DTO detalhado
     */
    RoomDetailDTO toDetailDto(Room entity);
    
    /**
     * Converte lista de entidades Room para lista de DTOs detalhados
     */
    List<RoomDetailDTO> toDetailDtoList(List<Room> entities);
    
    /**
     * Converte entidade Room para DTO resumido
     */
    @Mapping(target = "buildingName", source = "building.name")
    @Mapping(target = "resourceCount", expression = "java(entity.getResources().size())")
    RoomSummaryDTO toSummaryDto(Room entity);
    
    /**
     * Converte lista de entidades Room para lista de DTOs resumidos
     */
    List<RoomSummaryDTO> toSummaryDtoList(List<Room> entities);
    
    /**
     * Método auxiliar para converter um conjunto de recursos em um conjunto de IDs de recursos
     */
    @Named("resourcesToIds")
    default Set<Long> resourcesToIds(Set<Resource> resources) {
        if (resources == null) {
            return null;
        }
        return resources.stream()
                .map(Resource::getId)
                .collect(Collectors.toSet());
    }
}