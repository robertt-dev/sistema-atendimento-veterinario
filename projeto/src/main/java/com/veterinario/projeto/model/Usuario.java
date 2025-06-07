package com.veterinario.projeto.model;

import com.veterinario.projeto.model.Enum.Patente;
import com.veterinario.projeto.model.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

/**
 * Representa um usuário do sistema
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
    private Patente patente; // Pode ser null para usuários civis

    @Column(nullable = true, length = 100)
    private String nomeDeGuerra; // Pode ser null para usuários civis

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role perfilUsuariRole; // Papel do usuário no sistema (ADMIN, VETERINARIO, RECEPCAO)

    /**
     * Retorna a identificação militar formatada, ex: "CAP Morata".
     * Caso patente ou nomeDeGuerra estejam ausentes, retorna o que estiver disponível.
     * @return String com a identificação militar.
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
