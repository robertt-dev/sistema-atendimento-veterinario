package com.veterinario.projeto.repository;

import com.veterinario.projeto.model.Animal;
import com.veterinario.projeto.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositório para acessar e gerenciar dados da entidade Animal.
 */
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    /**
     * Lista todos os animais de um tutor.
     */
    List<Animal> findByTutor(Tutor tutor);

    /**
     * Busca animais cujo nome contenha o texto informado (ignora maiúsculas/minúsculas).
     */
    List<Animal> findByNomeIgnoreCaseContaining(String nome);
}

