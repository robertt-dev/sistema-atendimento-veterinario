package com.veterinario.projeto.model;

import com.veterinario.projeto.model.Enum.Patente;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o tutor de um ou mais animais.
 * Pode ser militar (com patente) ou civil (sem patente).
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "animais") // evita loop infinito com Animal
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // apenas ID será usado em comparação
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 11, unique = true, nullable = false)
    private String cpf;

    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(length = 255, unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_grad", nullable = true)
    private Patente postGrad;

    /**
     * Lista de animais relacionados a este tutor.
     */
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animais = new ArrayList<>();
}