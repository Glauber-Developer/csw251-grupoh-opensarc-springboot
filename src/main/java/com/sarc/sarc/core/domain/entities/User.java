package com.sarc.sarc.core.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nome;
    private String identificador;
    private String telefone;
    private String sexo;
    private LocalDate dataNascimento;
    @ManyToMany
    private List<ClassEntity> classes;
    
    @Enumerated(EnumType.STRING)
    private TipoPerfil perfil;

    public enum TipoPerfil {
        ADMIN, PROFESSOR, ALUNO, COORDENADOR
    }

    public User(Long id, String email, String nome, String identificador,
                String telefone, String sexo, LocalDate dataNascimento, TipoPerfil perfil) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.identificador = identificador;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.perfil = perfil;
    }
}