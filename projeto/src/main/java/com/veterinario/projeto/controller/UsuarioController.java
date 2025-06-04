package com.veterinario.projeto.controller;

import com.veterinario.projeto.model.Usuario;
import com.veterinario.projeto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Endpoints para manipular usuários.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Cadastrar um novo usuário.
     */
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.ok(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Listar todos os usuários.
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Buscar usuário por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retorna a identificação formatada do usuário: "Patente NomeDeGuerra"
     * Exemplo: "CAP Morata"
     */
    @GetMapping("/{id}/identificacao")
    public ResponseEntity<String> obterIdentificacaoFormatada(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarUsuarioPorId(id);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOpt.get();

        // Verifica se a patente e o nomeDeGuerra existem
        if (usuario.getPatente() == null || usuario.getNomeDeGuerra() == null || usuario.getNomeDeGuerra().isBlank()) {
            return ResponseEntity.badRequest().body("Patente ou nome de guerra não informados.");
        }

        String identificacao = usuario.getPatente().name() + " " + usuario.getNomeDeGuerra();
        return ResponseEntity.ok(identificacao);
    }

    /**
     * Atualizar um usuário existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        try {
            Usuario atualizado = usuarioService.atualizarUsuario(id, usuarioAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Deletar um usuário pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario.isPresent()) {
            usuarioService.excluirUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Buscar usuários pelo nome parcial.
     * Exemplo: /usuarios/buscar?nome=morata
     * Retorna todos os usuários que contêm "morata" no nome, ignorando maiúsculas/minúsculas.
     * 
     * @param nome - parte do nome para busca
     * @return lista de usuários que atendem ao filtro
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioService.buscarPorNomeContendo(nome);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content se nenhum usuário encontrado
        }
        return ResponseEntity.ok(usuarios); // 200 OK com a lista dos usuários encontrados
    }
}
