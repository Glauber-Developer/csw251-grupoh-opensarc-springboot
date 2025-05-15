package com.sarc.sarc;

import com.sarc.sarc.common.enums.*;
import com.sarc.sarc.core.domain.entities.*;
import com.sarc.sarc.infrastructure.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;
    private final ResourceRepository resourceRepository;
    private final CurriculumRepository curriculumRepository;
    private final DisciplineRepository disciplineRepository;
    private final ClassRepository classEntityRepository;
    private final LectureRepository lectureRepository;

    public DataInitializer(UserRepository userRepository, BuildingRepository buildingRepository, RoomRepository roomRepository, ResourceRepository resourceRepository, CurriculumRepository curriculumRepository, DisciplineRepository disciplineRepository, ClassRepository classEntityRepository, LectureRepository lectureRepository) {
        this.userRepository = userRepository;
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
        this.resourceRepository = resourceRepository;
        this.curriculumRepository = curriculumRepository;
        this.disciplineRepository = disciplineRepository;
        this.classEntityRepository = classEntityRepository;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Usuários
        User user1 = new User(1L,"ana@email.com", "Ana", "ana123", "51-99999-0001", "F", LocalDate.of(2001, 2, 10), User.TipoPerfil.ALUNO);
        User user2 = new User(2L,"bruno@email.com", "Bruno", "bruno456", "51-99999-0002", "M", LocalDate.of(2000, 8, 22), User.TipoPerfil.ALUNO);
        userRepository.saveAll(List.of(user1, user2));

        // Prédios
        Building b1 = new Building(3L,"Prédio Central", "Rua Alpha", "100", "Bloco A", "Centro", "POA", "RS", "90000-001",List.of());
        Building b2 = new Building(4L,"Prédio Secundário", "Rua Beta", "200", null, "Auxiliadora", "POA", "RS", "90000-002",List.of());
        buildingRepository.saveAll(List.of(b1, b2));

        // Salas
        Room r1 = new Room(5L,"Sala 101", 40, 1, b1,Set.of());
        Room r2 = new Room(6L,"Sala 202", 30, 2, b2,Set.of());
        roomRepository.saveAll(List.of(r1, r2));

        // Recursos
        Resource res1 = new Resource(7L,"Projetor", "Full HD HDMI", ResourceType.SOFTWARE, ResourceStatus.AVAILABLE, Set.of("HDMI", "Wi-Fi"),Set.of(r1));
        Resource res2 = new Resource(8L,"Lousa", "Lousa branca", ResourceType.FURNITURE, ResourceStatus.MAINTENANCE, Set.of("Caneta", "Apagador"),Set.of(r2));
        resourceRepository.saveAll(List.of(res1, res2));
        r1.getResources().add(res1);
        r2.getResources().add(res2);
        roomRepository.saveAll(List.of(r1, r2));

        // Currículo
        Curriculum cur1 = new Curriculum(9L,"Sistemas Distribuídos", new Date(), new Date(),List.of());
        Curriculum cur2 = new Curriculum(10L,"Inteligência Artificial", new Date(), new Date(),List.of());
        curriculumRepository.saveAll(List.of(cur1, cur2));

        // Disciplinas
        Discipline d1 = new Discipline(11L,"SD", 4, "Estudo de redes e SO", List.of("Tanenbaum"), cur1);
        Discipline d2 = new Discipline(12L,"IA", 5, "Redes neurais e aprendizado", List.of("Russell & Norvig"), cur1);
        disciplineRepository.saveAll(List.of(d1, d2));

        // Turmas
        ClassEntity c1 = new ClassEntity(13L,101, "SD", "Prof. Carlos", 8.0, 10.0, 30, "Prova Final",List.of(user1,user2), d1);
        classEntityRepository.saveAll(List.of(c1));
        user1.setClasses(List.of(c1));
        user2.setClasses(List.of(c1));
        userRepository.saveAll(List.of(user1, user2));

        // Aulas
        Lecture lec1 = new Lecture(15L,c1, "2025-05-10", "Threads e Sockets", 101, 10,new Attendance());
        lectureRepository.saveAll(List.of(lec1));

        System.out.println("Banco de dados inicializado com dados de teste.");
    }
}
