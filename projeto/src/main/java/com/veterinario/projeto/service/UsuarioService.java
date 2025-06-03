package com.veterinario.projeto.service;

import com.veterinario.projeto.model.Usuario;
import com.veterinario.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;





/**
 * Serviço para lidar com a lógica de usuários.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Cadastra um novo usuário, se o email ainda não existir.
     */
    public Usuario cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Lista todos os usuários.
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca usuário pelo ID.
     */
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Busca usuário pelo email.
     */
    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Exclui um usuário pelo ID.
     */
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Atualiza um usuário existente.
     */
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica se o novo email já está sendo usado por outro usuário
        Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByEmail(usuarioAtualizado.getEmail());
        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
            throw new RuntimeException("Email já está em uso por outro usuário.");
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setPatente(usuarioAtualizado.getPatente());

        return usuarioRepository.save(usuarioExistente);
    }
}
