package com.sarc.sarc.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entidade que representa uma sala no sistema.
 * Apenas administradores podem gerenciar salas.
 */
@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer capacity;
    
    @Column(nullable = false)
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
