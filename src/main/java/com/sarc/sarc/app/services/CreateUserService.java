package com.sarc.sarc.app.services;

import com.sarc.sarc.domain.entities.User;
import com.sarc.sarc.domain.entities.User.TipoPerfil;
import com.sarc.sarc.infrastructure.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CreateUserService {
    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id, String email, String nome, String identificador,
                      String telefone, String sexo, LocalDate dataNascimento, String perfil) {
        // Validação do perfil para garantir que seja um dos valores permitidos
        TipoPerfil tipoPerfil;
        try {
            tipoPerfil = TipoPerfil.valueOf(perfil.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Perfil inválido. Os perfis permitidos são: ADMIN, PROFESSOR, ALUNO, COORDENADOR");
        }
        
        User user = new User(id, email, nome, identificador, telefone, sexo, dataNascimento, tipoPerfil);
        return userRepository.save(user);
    }
}