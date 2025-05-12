package com.sarc.sarc.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.sarc.sarc.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um prédio no sistema.
 * Apenas administradores podem gerenciar prédios.
 */
@Entity
Tables(name = "buildings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String address;
    
    @Column(name = "building_number", nullable = false)
    private String buildingNumber;
    
    @Column
    private String complement;
    
    @Column
    private String district;
    
    @Column
    private String city;
    
    @Column
    private String state;
    
    @Column
    private String zipCode;
    
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

        public Building() {
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
