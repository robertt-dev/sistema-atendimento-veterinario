package com.veterinario.projeto.model;

import com.veterinario.projeto.model.Enum.Patente;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa um usuário do sistema (pode ser militar ou civil).
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Patente patente; // Pode ser null (usuário civil)
}