package com.veterinario.projeto.service;

import com.veterinario.projeto.model.Usuario;
import com.veterinario.projeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço para lidar com a lógica de usuários.
 * Aqui ficam as regras de negócio e interação com o banco via repository.
 */
@Service
public class UsuarioService {

    // Injeta o repositório para acessar os dados dos usuários no banco
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Cadastra um novo usuário no sistema.
     * Primeiro verifica se o email já existe para evitar duplicidade.
     * 
     * @param usuario - objeto com dados do usuário a cadastrar
     * @return o usuário salvo com ID gerado
     * @throws RuntimeException se o email já estiver cadastrado
     */
    public Usuario cadastrarUsuario(Usuario usuario) {
        // Verifica se o email informado já existe no banco
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        // Salva o usuário no banco e retorna o objeto salvo (com id gerado)
        return usuarioRepository.save(usuario);
    }

    /**
     * Retorna todos os usuários cadastrados no sistema.
     * 
     * @return lista com todos os usuários
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca um usuário pelo seu ID.
     * 
     * @param id - id do usuário a ser buscado
     * @return Optional com o usuário encontrado ou vazio se não existir
     */
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Busca um usuário pelo email.
     * Útil para processos de login e verificação.
     * 
     * @param email - email do usuário
     * @return Optional com o usuário ou vazio se não encontrado
     */
    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Exclui um usuário pelo ID.
     * 
     * @param id - id do usuário a ser removido
     */
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Atualiza os dados de um usuário já existente.
     * 
     * @param id - id do usuário a ser atualizado
     * @param usuarioAtualizado - objeto com os novos dados
     * @return usuário atualizado salvo no banco
     * @throws RuntimeException se usuário não encontrado ou email já em uso por outro
     */
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        // Busca o usuário existente pelo id, ou lança exceção se não achar
        Usuario usuarioExistente = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica se o novo email está em uso por outro usuário (diferente do atual)
        Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByEmail(usuarioAtualizado.getEmail());
        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
            throw new RuntimeException("Email já está em uso por outro usuário.");
        }

        // Atualiza os campos do usuário existente com os novos dados recebidos
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setPatente(usuarioAtualizado.getPatente());
        usuarioExistente.setNomeDeGuerra(usuarioAtualizado.getNomeDeGuerra());

        // Salva o usuário atualizado no banco e retorna ele
        return usuarioRepository.save(usuarioExistente);
    }

    /**
     * Busca usuários cujo nome contenha a string informada, ignorando maiúsculas e minúsculas.
     * 
     * @param nomeParcial - parte do nome para busca
     * @return lista de usuários que contenham o nomeParcial
     */
    public List<Usuario> buscarPorNomeContendo(String nomeParcial) {
        // Usa o método do repository para buscar por nome contendo a string, ignorando case
        return usuarioRepository.findByNomeContainingIgnoreCase(nomeParcial);
    }
}
