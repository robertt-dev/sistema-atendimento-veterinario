package com.veterinario.projeto.controller;

import com.veterinario.projeto.model.Prontuario;
import com.veterinario.projeto.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para operações de CRUD de Prontuários.
 */
@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    /**
     * Cria um novo prontuário para um animal, vinculado a um veterinário (usuário).
     */
    @PostMapping
    public ResponseEntity<Prontuario> criarProntuario(@RequestBody Prontuario prontuario) {
        Prontuario salvo = prontuarioService.salvar(prontuario);
        return ResponseEntity.ok(salvo);
    }

    /**
     * Lista todos os prontuários cadastrados no sistema.
     */
    @GetMapping
    public ResponseEntity<List<Prontuario>> listarTodos() {
        return ResponseEntity.ok(prontuarioService.listarTodos());
    }

    /**
     * Busca um prontuário pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable Long id) {
        Prontuario prontuario = prontuarioService.buscarPorId(id);
        if (prontuario != null) {
            return ResponseEntity.ok(prontuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deleta um prontuário por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prontuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
