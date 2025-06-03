package com.veterinario.projeto.model;

import com.veterinario.projeto.model.Enum.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa um agendamento de atendimento realizado por um recepcionista.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Data do agendamento.
     */
    @Column(nullable = false)
    private LocalDate dataAgend;

    /**
     * Hora do agendamento.
     */
    @Column(nullable = false)
    private LocalTime horaAgend;

    /**
     * Motivo do agendamento.
     */
    private String motivo;

    /**
     * Status do agendamento, conforme enum StatusAgendamento.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento statusAgend;

    /**
     * Animal relacionado ao agendamento.
     */
    @ManyToOne
    @JoinColumn(name = "animal_agend_id", nullable = false)
    private Animal animal;

    /**
     * Usuário (recepcionista) que realizou o agendamento.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_agend_id", nullable = false)
    private Usuario usuario;
}
