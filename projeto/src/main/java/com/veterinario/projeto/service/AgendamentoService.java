package com.veterinario.projeto.service;

import com.veterinario.projeto.model.Agendamento;
import com.veterinario.projeto.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    /**
     * Retorna todos os agendamentos cadastrados.
     */
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    /**
     * Busca um agendamento pelo ID.
     */
    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com o ID: " + id));
    }

    /**
     * Salva um novo agendamento.
     */
    public Agendamento salvarAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    /**
     * Atualiza um agendamento existente pelo ID.
     */
    public Agendamento atualizarAgendamento(Long id, Agendamento agendamento) {
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com o ID: " + id));

        agendamentoExistente.setDataAgend(agendamento.getDataAgend());
        agendamentoExistente.setHoraAgend(agendamento.getHoraAgend());
        agendamentoExistente.setMotivo(agendamento.getMotivo());
        agendamentoExistente.setStatusAgend(agendamento.getStatusAgend());
        agendamentoExistente.setAnimal(agendamento.getAnimal());
        agendamentoExistente.setUsuario(agendamento.getUsuario());

        return agendamentoRepository.save(agendamentoExistente);
    }

    /**
     * Deleta um agendamento pelo ID.
     */
    public void deletarAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com o ID: " + id));
        agendamentoRepository.delete(agendamento);
    }
}
