package com.veterinario.projeto.controller;

import com.veterinario.projeto.model.Agendamento;
import com.veterinario.projeto.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    /**
     * Lista todos os agendamentos.
     */
    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    /**
     * Busca um agendamento pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    /**
     * Cria um novo agendamento.
     */
    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(agendamentoService.salvarAgendamento(agendamento));
    }

    /**
     * Atualiza um agendamento existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(agendamentoService.atualizarAgendamento(id, agendamento));
    }

    /**
     * Deleta um agendamento pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}

