package com.sarc.sarc.app.controllers;


import com.sarc.sarc.app.services.CreateUserService;
import com.sarc.sarc.domain.entities.User;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping
    public String createUser(@RequestBody User request) {
        var user = createUserService.execute(
            request.getId(), request.getEmail(), request.getNome(), request.getIdentificador(),
            request.getTelefone(), request.getSexo(), request.getPerfil()
        );
        return "Usuário criado com ID: " + user.getId();
    }
}

