package com.sarc.sarc.app.controller.services;


import com.sarc.sarc.domain.Building;
import com.sarc.sarc.domain.Room;
import com.sarc.sarc.infrastructure.BuildingRepository;
import com.sarc.sarc.infrastructure.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para operações relacionadas a prédios.
 */
@Service
public class BuildingService {
    
    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;
    
    @Autowired
    public BuildingService(BuildingRepository buildingRepository, RoomRepository roomRepository) {
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
    }
    
    /**
     * Obtém todos os prédios cadastrados
     * @return Lista de prédios
     */
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }
    
    /**
     * Busca um prédio pelo ID
     * @param id ID do prédio
     * @return Prédio encontrado ou vazio
     */
    public Optional<Building> getBuildingById(Long id) {
        return buildingRepository.findById(id);
    }
    
    /**
     * Busca prédios por nome
     * @param name Nome ou parte do nome do prédio
     * @return Lista de prédios encontrados
     */
    public List<Building> getBuildingsByName(String name) {
        return buildingRepository.findByNameContainingIgnoreCase(name);
    }
    
    /**
     * Cria um novo prédio
     * @param building Prédio a ser criado
     * @return Prédio criado
     */
    @Transactional
    public Building createBuilding(Building building) {
        // Verifica se já existe um prédio com o mesmo número
        if (buildingRepository.existsByBuildingNumber(building.getBuildingNumber())) {
            throw new IllegalArgumentException("Já existe um prédio com o número: " + building.getBuildingNumber());
        }
        return buildingRepository.save(building);
    }
    
    /**
     * Atualiza um prédio existente
     * @param id ID do prédio a ser atualizado
     * @param buildingDetails Novas informações do prédio
     * @return Prédio atualizado ou vazio se não encontrado
     */
    @Transactional
    public Optional<Building> updateBuilding(Long id, Building buildingDetails) {
        return buildingRepository.findById(id).map(building -> {
            building.setName(buildingDetails.getName());
            building.setAddress(buildingDetails.getAddress());
            building.setBuildingNumber(buildingDetails.getBuildingNumber());
            building.setComplement(buildingDetails.getComplement());
            building.setDistrict(buildingDetails.getDistrict());
            building.setCity(buildingDetails.getCity());
            building.setState(buildingDetails.getState());
            building.setZipCode(buildingDetails.getZipCode());
            return buildingRepository.save(building);
        });
    }
    
    /**
     * Remove um prédio pelo ID
     * @param id ID do prédio a ser removido
     * @return true se removido com sucesso, false se não encontrado
     */
    @Transactional
    public boolean deleteBuilding(Long id) {
        return buildingRepository.findById(id).map(building -> {
            buildingRepository.delete(building);
            return true;
        }).orElse(false);
    }
    
    /**
     * Obtém todas as salas de um prédio
     * @param buildingId ID do prédio
     * @return Lista de salas do prédio
     */
    public List<Room> getRoomsByBuilding(Long buildingId) {
        return roomRepository.findByBuildingId(buildingId);
    }
}
