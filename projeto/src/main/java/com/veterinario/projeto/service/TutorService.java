package com.veterinario.projeto.service;

import com.veterinario.projeto.model.Animal;
import com.veterinario.projeto.model.Tutor;
import com.veterinario.projeto.repository.AnimalRepository;
import com.veterinario.projeto.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Lógica de negócio para tutores e seus animais.
 */
@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private AnimalRepository animalRepository;

    /**
     * Cadastra um novo tutor no sistema.
     */
    public Tutor cadastrarTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    /**
     * Busca um tutor pelo CPF.
     */
    public Optional<Tutor> buscarTutorPorCpf(String cpf) {
        return tutorRepository.findByCpf(cpf);
    }

    /**
     * Lista todos os animais de um tutor.
     */
    public List<Animal> listarAnimaisDoTutor(Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        return animalRepository.findByTutor(tutor);
    }

    /**
     * Cadastra um novo animal vinculado a um tutor.
     */
    public Animal cadastrarAnimal(Long tutorId, Animal animal) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));

        animal.setTutor(tutor); // Relaciona o animal ao tutor
        return animalRepository.save(animal);
    }
}
