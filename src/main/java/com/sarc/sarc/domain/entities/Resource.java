package com.sarc.sarc.domain.entities;

import java.util.HashSet;
import java.util.Set;

import com.sarc.sarc.domain.enums.ResourceStatus;
import com.sarc.sarc.domain.enums.ResourceType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Getter
@Setter
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
}
