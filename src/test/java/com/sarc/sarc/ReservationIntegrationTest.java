package com.sarc.sarc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarc.sarc.common.enums.ResourceStatus;
import com.sarc.sarc.common.enums.ResourceType;
import com.sarc.sarc.core.domain.entities.Lecture;
import com.sarc.sarc.core.domain.entities.Resource;
import com.sarc.sarc.core.domain.entities.Reservation;
import com.sarc.sarc.infrastructure.ResourceRepository;
import com.sarc.sarc.infrastructure.LectureRepository;
import com.sarc.sarc.infrastructure.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Inject your repositories here
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testCreateAndGetReservation() throws Exception {
        // Create and save real Lecture entity
        Lecture lecture = new Lecture();
        // set necessary fields on lecture here, e.g. lecture.setName("Some lecture");
        lecture = lectureRepository.save(lecture);

        // Create and save real Resource entity
        Resource resource = new Resource();
        resource.setName("Test Resource");
        resource.setType(ResourceType.EQUIPMENT);    
        resource.setStatus(ResourceStatus.AVAILABLE);
        
        // set necessary fields on resource here, e.g. resource.setName("Some resource");
        resource = resourceRepository.save(resource);

        Reservation reservation = new Reservation();
        reservation.setDateTime(LocalDateTime.now().plusDays(1));
        reservation.setTimeSlot("10:00 - 11:00");
        reservation.setObservations("Integration test");
        reservation.setLecture(lecture);    // use saved lecture
        reservation.setResource(resource);  // use saved resource

        // Serialize reservation to JSON
        String json = objectMapper.writeValueAsString(reservation);

        // Perform POST request to create reservation
        mockMvc.perform(post("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.timeSlot").value("10:00 - 11:00"));

        // Perform GET request to verify reservation was saved
        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].timeSlot").value("10:00 - 11:00"));
    }

    @Test
void testUpdateReservation() throws Exception {
    // Primeiro, cria e salva uma reserva para atualizar depois
    Lecture lecture = new Lecture();
    lecture = lectureRepository.save(lecture);

    Resource resource = new Resource();
    resource.setName("Resource for update");
    resource.setType(ResourceType.EQUIPMENT);
    resource.setStatus(ResourceStatus.AVAILABLE);
    resource = resourceRepository.save(resource);

    Reservation reservation = new Reservation();
    reservation.setDateTime(LocalDateTime.now().plusDays(2));
    reservation.setTimeSlot("14:00 - 15:00");
    reservation.setObservations("Original observations");
    reservation.setLecture(lecture);
    reservation.setResource(resource);
    reservation = reservationRepository.save(reservation);

    // Modificar dados para atualizar
    reservation.setTimeSlot("15:00 - 16:00");
    reservation.setObservations("Updated observations");

    String json = objectMapper.writeValueAsString(reservation);

    // Executa PUT para atualizar
    mockMvc.perform(put("/api/reservations/{id}", reservation.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.timeSlot").value("15:00 - 16:00"))
            .andExpect(jsonPath("$.observations").value("Updated observations"));
}

@Test
void testDeleteReservation() throws Exception {
    // Criar e salvar reserva para deletar
    Lecture lecture = new Lecture();
    lecture = lectureRepository.save(lecture);

    Resource resource = new Resource();
    resource.setName("Resource for delete");
    resource.setType(ResourceType.EQUIPMENT);
    resource.setStatus(ResourceStatus.AVAILABLE);
    resource = resourceRepository.save(resource);

    Reservation reservation = new Reservation();
    reservation.setDateTime(LocalDateTime.now().plusDays(3));
    reservation.setTimeSlot("16:00 - 17:00");
    reservation.setObservations("To be deleted");
    reservation.setLecture(lecture);
    reservation.setResource(resource);
    reservation = reservationRepository.save(reservation);

    // Executa DELETE e espera noContent (204)
    mockMvc.perform(delete("/api/reservations/{id}", reservation.getId()))
            .andExpect(status().isNoContent());

    // Verificar que a reserva foi realmente removida (espera 404)
    mockMvc.perform(get("/api/reservations/{id}", reservation.getId()))
            .andExpect(status().isNotFound());
}
}
