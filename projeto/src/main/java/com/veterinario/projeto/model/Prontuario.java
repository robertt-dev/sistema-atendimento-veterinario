package com.veterinario.projeto.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Representa um prontuário médico veterinário associado a um animal e um usuário (veterinário).
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"animal", "usuario"}) // evita recursão infinita ao imprimir
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private LocalDate dataPront;

    @Column(columnDefinition = "TEXT")
    private String anamnese;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamento;

    @Column(columnDefinition = "TEXT")
    private String prescricao;

    // Identificador formatado: 00001/2025/SCG
    @Column(nullable = false, unique = true, length = 20)
    private String numero;

    /**
     * Animal ao qual este prontuário está associado.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_pront_id", nullable = false)
    private Animal animal;

    /**
     * Veterinário (usuário) que criou este prontuário.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_pront_id", nullable = false)
    private Usuario usuario;
}
