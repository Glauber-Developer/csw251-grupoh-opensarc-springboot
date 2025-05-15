package com.sarc.sarc.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarc.sarc.core.domain.entities.User;
import com.sarc.sarc.core.services.CreateUserService;
import com.sarc.sarc.core.services.UpdateUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;

    public UserController(CreateUserService createUserService, UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.updateUserService = updateUserService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User request) {
        try {
            var user = createUserService.execute(
                request.getId(), request.getEmail(), request.getNome(), request.getIdentificador(),
                request.getTelefone(), request.getSexo(), request.getDataNascimento(), request.getPerfil().toString()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com ID: " + user.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, 
                                            @RequestBody User request
                                            ) {
        // Verificar se o usuário que está fazendo a requisição é um ADMIN
        try {
            //TipoPerfil perfilRequisitante = TipoPerfil.valueOf(userPerfil.toUpperCase());
            
            //if (perfilRequisitante != TipoPerfil.ADMIN) {
            //    return ResponseEntity.status(HttpStatus.FORBIDDEN)
            //        .body("Apenas administradores podem editar usuários.");
            //}
            
            var updatedUser = updateUserService.execute(
                id, request.getEmail(), request.getNome(), request.getIdentificador(),
                request.getTelefone(), request.getSexo(), request.getDataNascimento(), request.getPerfil().toString()
            );
            
            return ResponseEntity.ok("Usuário atualizado com sucesso: " + updatedUser.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}