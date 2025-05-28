package com.veterinario.projeto.model;

import com.veterinario.projeto.model.Enum.Patente;
import jakarta.persistence.*;

import java.util.UUID;
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Patente patente;
}



/* Usuário do sistema
CREATE TABLE usuario (
        id SERIAL PRIMARY KEY,
        nome TEXT NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL,
        senha TEXT NOT NULL,
   tipo_usuario TEXT CHECK (tipo_usuario IN ('admin', 'vet', 'recep')) NOT NULL,
   post_grad_id INT REFERENCES posto_graduacao(id)
);\*
