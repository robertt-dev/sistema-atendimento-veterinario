package com.veterinario.projeto.model;

import com.veterinario.projeto.model.Enum.OrigemAnimal;
import com.veterinario.projeto.model.Enum.TemperamentoAnimal;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa um animal atendido pela clínica veterinária.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "tutor") // evita recursão infinita ao imprimir
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String especie;

    private String raca;

    @Column(name = "cor_pelo")
    private String corPelo;

    @Column(nullable = false)
    private String sexo;

    @Column(name = "idade_animal")
    private Integer idadeAnimal;

    @Enumerated(EnumType.STRING)
    @Column(name = "de_onde", nullable = false)
    private OrigemAnimal origem;

    @Enumerated(EnumType.STRING)
    @Column(name = "temp_animal", nullable = false)
    private TemperamentoAnimal temperamento;

    @Column(nullable = false)
    private Boolean castrado;

    /**
     * Tutor responsável pelo animal.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    @JsonBackReference
    private Tutor tutor;
}
