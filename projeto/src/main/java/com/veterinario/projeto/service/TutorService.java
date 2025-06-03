package com.veterinario.projeto.service;

import com.veterinario.projeto.model.Tutor;
import com.veterinario.projeto.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    /**
     * Retorna todos os tutores cadastrados.
     */
    public List<Tutor> listarTutores() {
        return tutorRepository.findAll();
    }

    /**
     * Retorna um tutor pelo ID.
     */
    public Tutor buscarPorId(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + id));
    }

    /**
     * Cadastra um novo tutor.
     */
    public Tutor salvarTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    /**
     * Atualiza as informações de um tutor existente pelo ID.
     */
    public Tutor atualizarTutor(Long id, Tutor tutor) {
        Tutor tutorExistente = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + id));

        tutorExistente.setNome(tutor.getNome());
        tutorExistente.setCpf(tutor.getCpf());
        tutorExistente.setTelefone(tutor.getTelefone());
        tutorExistente.setEmail(tutor.getEmail());
        tutorExistente.setPostGrad(tutor.getPostGrad());

        return tutorRepository.save(tutorExistente);
    }

    /**
     * Remove um tutor pelo ID. Os animais associados são removidos automaticamente em cascata.
     */
    public void deletarTutor(Long id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + id));
        tutorRepository.delete(tutor);
    }
}
