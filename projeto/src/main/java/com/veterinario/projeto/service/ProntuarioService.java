package com.veterinario.projeto.service;

import com.veterinario.projeto.model.Animal;
import com.veterinario.projeto.model.Prontuario;
import com.veterinario.projeto.model.Usuario;
import com.veterinario.projeto.repository.AnimalRepository;
import com.veterinario.projeto.repository.ProntuarioRepository;
import com.veterinario.projeto.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Serviço responsável pelas regras de negócio relacionadas ao Prontuário.
 */
@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final AnimalRepository animalRepository;
    private final UsuarioRepository usuarioRepository;

    private static final String UNIDADE = "SCG"; // sigla fixa da clínica

    public ProntuarioService(ProntuarioRepository prontuarioRepository, AnimalRepository animalRepository, UsuarioRepository usuarioRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.animalRepository = animalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria e salva um novo prontuário com número gerado automaticamente.
     */
    public Prontuario salvarProntuario(Prontuario prontuario, Long animalId, Long usuarioId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        prontuario.setAnimal(animal);
        prontuario.setUsuario(usuario);

        if (prontuario.getDataPront() == null) {
            prontuario.setDataPront(LocalDate.now());
        }

        // Geração do número do prontuário
        String numero = gerarNumeroProntuario(prontuario.getDataPront());
        prontuario.setNumero(numero);

        return prontuarioRepository.save(prontuario);
    }

    /**
     * Salva prontuário com base no objeto pronto.
     * Regras de validação mínimas: animal e usuário devem estar presentes.
     */
    public Prontuario salvar(Prontuario prontuario) {
        if (prontuario.getAnimal() == null || prontuario.getAnimal().getId() == null) {
            throw new IllegalArgumentException("ID do animal é obrigatório.");
        }

        if (prontuario.getUsuario() == null || prontuario.getUsuario().getId() == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório.");
        }

        Animal animal = animalRepository.findById(prontuario.getAnimal().getId())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        Usuario usuario = usuarioRepository.findById(prontuario.getUsuario().getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        prontuario.setAnimal(animal);
        prontuario.setUsuario(usuario);

        if (prontuario.getDataPront() == null) {
            prontuario.setDataPront(LocalDate.now());
        }

        String numero = gerarNumeroProntuario(prontuario.getDataPront());
        prontuario.setNumero(numero);

        return prontuarioRepository.save(prontuario);
    }

    /**
     * Busca um prontuário por ID.
     */
    public Prontuario buscarPorId(Long id) {
        return prontuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prontuário não encontrado com ID: " + id));
    }

    /**
     * Remove um prontuário por ID.
     */
    public void deletar(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Prontuário não encontrado para exclusão com ID: " + id);
        }
        prontuarioRepository.deleteById(id);
    }

    /**
     * Lista todos os prontuários.
     */
    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    /**
     * Gera um número de prontuário no formato 00001/ANO/SCG
     */
    private String gerarNumeroProntuario(LocalDate data) {
        int ano = data.getYear();
        Long count = prontuarioRepository.countByAno(ano);
        long sequencial = count + 1;
        return String.format("%05d/%d/%s", sequencial, ano, UNIDADE);
    }
}
