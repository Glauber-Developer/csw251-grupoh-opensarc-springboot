package com.sarc.sarc.domain.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;


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
    private String perfil; // Admin, Professor, Aluno, Coordenador

    public User(Long id, String email, String nome, String identificador,
                String telefone, String sexo, String perfil) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.identificador = identificador;
        this.telefone = telefone;
        this.sexo = sexo;
        this.perfil = perfil;
    }
}
