package com.sarc.sarc.domain.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidade que representa um prédio no sistema.
 * Apenas administradores podem gerenciar prédios.
 */
@Entity
@Table(name = "buildings")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Building {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @jakarta.persistence.Column(nullable = false)
    private String name;
    
    @jakarta.persistence.Column(nullable = false)
    private String address;
    
    @jakarta.persistence.Column(name = "building_number", nullable = false)
    private String buildingNumber;
    
    @jakarta.persistence.Column
    private String complement;
    
    @jakarta.persistence.Column
    private String district;
    
    @jakarta.persistence.Column
    private String city;
    
    @jakarta.persistence.Column
    private String state;
    
    @jakarta.persistence.Column
    private String zipCode;
    
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();
    
    public Building(Long id, String name, String address, String buildingNumber, 
                   String complement, String district, String city, 
                   String state, String zipCode, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.buildingNumber = buildingNumber;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.rooms = rooms;
    }
    
    /**
     * Adiciona uma sala ao prédio
     * @param room A sala a ser adicionada
     */
    public void addRoom(Room room) {
        rooms.add(room);
        room.setBuilding(this);
    }
    
    /**
     * Remove uma sala do prédio
     * @param room A sala a ser removida
     */
    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setBuilding(null);
    }
}
