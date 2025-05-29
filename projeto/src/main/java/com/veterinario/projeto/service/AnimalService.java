package com.veterinario.projeto.service;

import com.veterinario.projeto.model.Animal;
import com.veterinario.projeto.model.Tutor;
import com.veterinario.projeto.repository.AnimalRepository;
import com.veterinario.projeto.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TutorRepository tutorRepository;

    /**
     * Cadastra um novo animal e associa ao tutor pelo tutorId.
     */
    public Animal cadastrarAnimal(Long tutorId, Animal animal) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        animal.setTutor(tutor);
        return animalRepository.save(animal);
    }

    /**
     * Busca animal pelo id.
     */
    public Optional<Animal> buscarAnimalPorId(Long id) {
        return animalRepository.findById(id);
    }

    /**
     * Lista todos os animais.
     */
    public List<Animal> listarTodosAnimais() {
        return animalRepository.findAll();
    }

    /**
     * Lista todos os animais de um tutor específico.
     */
    public List<Animal> listarAnimaisPorTutor(Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        return animalRepository.findByTutor(tutor);
    }

    /**
     * Busca animais pelo nome (contendo o texto, ignorando maiúsculas/minúsculas).
     */
    public List<Animal> buscarAnimaisPorNome(String nome) {
        return animalRepository.findByNomeIgnoreCaseContaining(nome);
    }
}
