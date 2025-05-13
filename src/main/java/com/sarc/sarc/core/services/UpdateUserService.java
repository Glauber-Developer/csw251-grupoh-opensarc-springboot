package com.sarc.sarc.core.services;

import com.sarc.sarc.core.domain.entities.User;
import com.sarc.sarc.core.domain.entities.User.TipoPerfil;
import com.sarc.sarc.infrastructure.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UpdateUserService {
    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id, String email, String nome, String identificador,
                      String telefone, String sexo, LocalDate dataNascimento, String perfil) {
        // Verificar se o usuário existe
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + id);
        }

        // Validação do perfil para garantir que seja um dos valores permitidos
        TipoPerfil tipoPerfil;
        try {
            tipoPerfil = TipoPerfil.valueOf(perfil.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Perfil inválido. Os perfis permitidos são: ADMIN, PROFESSOR, ALUNO, COORDENADOR");
        }

        // Atualizar os dados do usuário
        User userToUpdate = existingUser.get();
        userToUpdate.setEmail(email);
        userToUpdate.setNome(nome);
        userToUpdate.setIdentificador(identificador);
        userToUpdate.setTelefone(telefone);
        userToUpdate.setSexo(sexo);
        userToUpdate.setDataNascimento(dataNascimento);
        userToUpdate.setPerfil(tipoPerfil);

        return userRepository.save(userToUpdate);
    }
}