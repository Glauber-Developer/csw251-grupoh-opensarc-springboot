package com.sarc.sarc.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidade que representa uma sala no sistema.
 * Apenas administradores podem gerenciar salas.
 */
@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Integer capacity;
    private Integer floor;
    
    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    @ToString.Exclude
    private Building building;
    
    @ManyToMany
    @JoinTable(
        name = "room_resources",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Resource> resources = new HashSet<>();
    
    public Room(Long id, String name, Integer capacity, Integer floor, 
               Building building, Set<Resource> resources) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.floor = floor;
        this.building = building;
        this.resources = resources;
    }
    
    /**
     * Adiciona um recurso à sala
     * @param resource O recurso a ser adicionado
     */
    public void addResource(Resource resource) {
        resources.add(resource);
    }
    
    /**
     * Remove um recurso da sala
     * @param resource O recurso a ser removido
     */
    public void removeResource(Resource resource) {
        resources.remove(resource);
    }
}
