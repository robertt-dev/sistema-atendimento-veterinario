package com.veterinario.projeto.repository;

import com.veterinario.projeto.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório para acessar e gerenciar dados da entidade Tutor.
 */
public interface TutorRepository extends JpaRepository<Tutor, Long> {

    // Busca tutor por CPF
    Optional<Tutor> findByCpf(String cpf);

    // Busca tutor por e-mail
    Optional<Tutor> findByEmail(String email);
}
