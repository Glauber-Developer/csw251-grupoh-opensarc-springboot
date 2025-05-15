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
import com.sarc.sarc.core.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/users")
@Tag(name= "Usuários", description = "API para gerenciamento de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> listAllUsers() {
        Iterable<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User request) {
        try {
            ResponseEntity<User> userResponse = userService.createUser(
                request.getId(), request.getEmail(), request.getNome(), request.getIdentificador(),
                request.getTelefone(), request.getSexo(), request.getDataNascimento(), request.getPerfil().toString()
            );
            User user = userResponse.getBody();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao criar o usuário.");
            }
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
            
            ResponseEntity<User> updatedUserResponse = userService.updatUser(
                id, request.getEmail(), request.getNome(), request.getIdentificador(),
                request.getTelefone(), request.getSexo(), request.getDataNascimento(), request.getPerfil().toString()
            );
            User updatedUser = updatedUserResponse.getBody();
            if (updatedUser == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao atualizar o usuário.");
            }
            return ResponseEntity.ok("Usuário atualizado com sucesso: " + updatedUser.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}