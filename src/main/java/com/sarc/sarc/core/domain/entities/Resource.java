package com.sarc.sarc.core.domain.entities;

import java.util.HashSet;
import java.util.Set;

import com.sarc.sarc.common.enums.ResourceStatus;
import com.sarc.sarc.common.enums.ResourceType;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidade que representa um recurso no sistema.
 * Recursos podem ser associados a salas.
 */
@Entity
@Table(name = "resources")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Resource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private ResourceType type;
    
    @Column(nullable = false)
    private ResourceStatus status;
    
    @CollectionTable(name = "resource_characteristics", 
                    joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "characteristic")
    @ElementCollection
    private Set<String> characteristics = new HashSet<>();
    
    @ManyToMany(mappedBy = "resources")
    private Set<Room> rooms = new HashSet<>();
    
    public Resource(Long id, String name, String description, ResourceType type,
                   ResourceStatus status, Set<String> characteristics, Set<Room> rooms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
        this.characteristics = characteristics;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public Set<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<String> characteristics) {
        this.characteristics = characteristics;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
