package com.veterinario.projeto.controller;

import com.veterinario.projeto.model.Tutor;
import com.veterinario.projeto.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    /**
     * Lista todos os tutores cadastrados.
     */
    @GetMapping
    public ResponseEntity<List<Tutor>> listarTutores() {
        return ResponseEntity.ok(tutorService.listarTutores());
    }

    /**
     * Busca um tutor pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tutor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.buscarPorId(id));
    }

    /**
     * Cadastra um novo tutor.
     */
    @PostMapping
    public ResponseEntity<Tutor> salvarTutor(@RequestBody Tutor tutor) {
        return ResponseEntity.ok(tutorService.salvarTutor(tutor));
    }

    /**
     * Atualiza um tutor existente pelo ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tutor> atualizarTutor(@PathVariable Long id, @RequestBody Tutor tutor) {
        return ResponseEntity.ok(tutorService.atualizarTutor(id, tutor));
    }

    /**
     * Deleta um tutor pelo ID. Os animais associados são removidos em cascata.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTutor(@PathVariable Long id) {
        tutorService.deletarTutor(id);
        return ResponseEntity.noContent().build();
    }
}
