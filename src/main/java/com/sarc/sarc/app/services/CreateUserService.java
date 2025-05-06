package com.sarc.sarc.app.services;


import com.sarc.sarc.domain.entities.User;
import com.sarc.sarc.infrastructure.UserRepository;


public class CreateUserService {
    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id, String email, String nome, String identificador,
                        String telefone, String sexo, String perfil) {
        User user = new User(id, email, nome, identificador, telefone, sexo, perfil);
        return userRepository.save(user);
    }
}
