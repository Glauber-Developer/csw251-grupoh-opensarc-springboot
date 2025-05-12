package com.sarc.sarc.domain;

import com.sarc.sarc.domain.enums.ResourceStatus;
import com.sarc.sarc.domain.enums.ResourceType;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um recurso no sistema.
 * Recursos podem ser associados a salas.
 */
@Entity
@Table(name = "resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType type;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceStatus status;
    
    @ElementCollection
    @CollectionTable(name = "resource_characteristics", 
                    joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "characteristic")
    private Set<String> characteristics = new HashSet<>();
    
    @ManyToMany(mappedBy = "resources")
    private Set<Room> rooms = new HashSet<>();
}
