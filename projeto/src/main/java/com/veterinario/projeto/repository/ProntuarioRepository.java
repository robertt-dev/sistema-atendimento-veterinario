package com.veterinario.projeto.repository;

import com.veterinario.projeto.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

    /**
     * Conta quantos prontuários já existem para um determinado ano.
     */
    @Query("SELECT COUNT(p) FROM Prontuario p WHERE YEAR(p.dataPront) = :ano")
    Long countByAno(int ano);
}
