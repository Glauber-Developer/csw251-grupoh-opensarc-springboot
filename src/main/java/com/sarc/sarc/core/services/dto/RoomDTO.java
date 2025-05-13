package com.sarc.sarc.core.services.dto;

import com.sarc.sarc.domain.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO para criação e atualização de salas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    
    private Long id;
    
    @NotBlank(message = "O nome da sala é obrigatório")
    @Size(max = 100, message = "O nome da sala deve ter no máximo 100 caracteres")
    private String name;
    
    @NotNull(message = "A capacidade da sala é obrigatória")
    @Min(value = 1, message = "A capacidade deve ser maior ou igual a 1")
    private Integer capacity;
    
    @NotNull(message = "O andar da sala é obrigatório")
    private Integer floor;
    
    private Long buildingId;
    
    private Set<Long> resourceIds = new HashSet<>();
}

/**
 * DTO com informações detalhadas de uma sala
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetailDTO {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer floor;
    private BuildingSummaryDTO building;
    private Set<ResourceSummaryDTO> resources = new HashSet<>();
}

/**
 * DTO com informações resumidas de uma sala
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSummaryDTO {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer floor;
    private String buildingName;
    private int resourceCount;
}

