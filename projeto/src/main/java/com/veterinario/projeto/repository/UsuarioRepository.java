package com.veterinario.projeto.repository;

import com.veterinario.projeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório para acesso aos dados da entidade Usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, java.util.UUID> {

    // Buscar por e-mail (útil para login)
    Optional<Usuario> findByEmail(String email);

    // Verifica se um e-mail já está cadastrado
    boolean existsByEmail(String email);
}