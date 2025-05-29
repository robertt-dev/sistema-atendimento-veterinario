package com.veterinario.projeto.controller;

import com.veterinario.projeto.model.Animal;
import com.veterinario.projeto.model.Tutor;
import com.veterinario.projeto.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints REST para manipulação de tutores e seus animais.
 */
@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    /**
     * Cadastra um novo tutor.
     */
    @PostMapping
    public ResponseEntity<Tutor> cadastrarTutor(@RequestBody Tutor tutor) {
        Tutor novoTutor = tutorService.cadastrarTutor(tutor);
        return ResponseEntity.ok(novoTutor);
    }

    /**
     * Busca tutor por CPF.
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Tutor> buscarPorCpf(@PathVariable String cpf) {
        return tutorService.buscarTutorPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca tutores por nome (parcial e sem case-sensitive).
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Tutor>> buscarPorNome(@PathVariable String nome) {
        List<Tutor> tutores = tutorService.buscarTutoresPorNome(nome);
        return ResponseEntity.ok(tutores);
    }

    /**
     * Lista todos os animais de um tutor.
     */
    @GetMapping("/{tutorId}/animais")
    public ResponseEntity<List<Animal>> listarAnimaisDoTutor(@PathVariable Long tutorId) {
        List<Animal> animais = tutorService.listarAnimaisDoTutor(tutorId);
        return ResponseEntity.ok(animais);
    }

    /**
     * Cadastra um novo animal vinculado a um tutor.
     */
    @PostMapping("/{tutorId}/animais")
    public ResponseEntity<Animal> cadastrarAnimal(@PathVariable Long tutorId, @RequestBody Animal animal) {
        Animal novoAnimal = tutorService.cadastrarAnimal(tutorId, animal);
        return ResponseEntity.ok(novoAnimal);
    }
}


