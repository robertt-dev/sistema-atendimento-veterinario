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

    @Column(nullable = true, length = 100)
    private String nomeDeGuerra; // Nome de guerra do militar, pode ser null para civis

    /**
     * Retorna a identificação militar formatada, ex: "CAP Morata".
     * Se patente ou nomeDeGuerra for null, retorna apenas o que estiver disponível.
     */
    public String getIdentificacaoMilitar() {
        StringBuilder sb = new StringBuilder();
        if (patente != null) {
            sb.append(patente.getLabel());
        }
        if (nomeDeGuerra != null && !nomeDeGuerra.isBlank()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(nomeDeGuerra);
        }
        return sb.toString();
    }
}
