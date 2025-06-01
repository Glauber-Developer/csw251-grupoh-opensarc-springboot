package com.sarc.sarc.core.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sarc.sarc.core.domain.entities.User;
import com.sarc.sarc.core.domain.entities.User.TipoPerfil;
import com.sarc.sarc.infrastructure.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> createUser(Long id, String email, String nome, String identificador,
                      String telefone, String sexo, LocalDate dataNascimento, String perfil) {
        // Validação do perfil para garantir que seja um dos valores permitidos
        TipoPerfil tipoPerfil;
        try {
            tipoPerfil = TipoPerfil.valueOf(perfil.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Perfil inválido. Os perfis permitidos são: ADMIN, PROFESSOR, ALUNO, COORDENADOR");
        }
        
        User user = new User(id, email, nome, identificador, telefone, sexo, dataNascimento, tipoPerfil);
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> updatUser(Long id, String email, String nome, String identificador,
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

        return ResponseEntity.ok(userRepository.save(userToUpdate));
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
