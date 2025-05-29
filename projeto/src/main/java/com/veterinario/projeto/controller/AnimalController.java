package com.veterinario.projeto.controller;

import com.veterinario.projeto.model.Animal;
import com.veterinario.projeto.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller responsável pelos endpoints relacionados à entidade Animal.
 */
@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    /**
     * Cadastra um novo animal associado a um tutor existente.
     *
     * @param tutorId ID do tutor ao qual o animal será vinculado
     * @param animal Dados do animal a ser cadastrado
     * @return Animal salvo ou erro caso tutor não exista
     */
    @PostMapping("/tutor/{tutorId}")
    public ResponseEntity<Animal> cadastrarAnimal(@PathVariable Long tutorId, @RequestBody Animal animal) {
        try {
            Animal novoAnimal = animalService.cadastrarAnimal(tutorId, animal);
            return ResponseEntity.ok(novoAnimal);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Busca um animal pelo seu ID.
     *
     * @param id ID do animal
     * @return Animal encontrado ou 404 caso não exista
     */
    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarAnimalPorId(@PathVariable Long id) {
        Optional<Animal> animal = animalService.buscarAnimalPorId(id);
        return animal.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos os animais cadastrados no sistema.
     *
     * @return Lista de animais
     */
    @GetMapping
    public ResponseEntity<List<Animal>> listarTodosAnimais() {
        List<Animal> animais = animalService.listarTodosAnimais();
        return ResponseEntity.ok(animais);
    }

    /**
     * Lista todos os animais vinculados a um tutor específico.
     *
     * @param tutorId ID do tutor
     * @return Lista de animais do tutor ou erro se tutor não existir
     */
    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<Animal>> listarAnimaisPorTutor(@PathVariable Long tutorId) {
        try {
            List<Animal> animais = animalService.listarAnimaisPorTutor(tutorId);
            return ResponseEntity.ok(animais);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Busca animais por nome (busca parcial e case insensitive).
     *
     * @param nome Nome ou parte do nome do animal
     * @return Lista de animais que correspondem à busca
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Animal>> buscarPorNome(@PathVariable String nome) {
        List<Animal> animais = animalService.buscarAnimaisPorNome(nome);
        return ResponseEntity.ok(animais);
    }
}
