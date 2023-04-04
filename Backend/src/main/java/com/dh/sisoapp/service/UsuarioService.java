package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.model.Usuario;
import com.dh.sisoapp.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private IUsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void salvar(Usuario usuario) {
        try {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                throw new IllegalArgumentException("Já existe um usuário cadastrado com este e-mail");
            }

            usuarioRepository.save(usuario);
            Util.escreveLog("Usuário salvo com sucesso: " + usuario);
        } catch (IllegalArgumentException e) {
            Util.escreveLog("Erro ao salvar usuário: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            Util.escreveLog("Erro interno ao salvar usuário: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno ao salvar usuário", e);
        }
    }
    public List<Usuario> listar() {
        Util.escreveLog("Listando todos usuarios...");
        List<Usuario> usuarios = usuarioRepository.findAll();
        Util.escreveLog("Total de usuarios encontrados: " + usuarios.size());
        return usuarios;
    }

    public ResponseEntity<Object> buscarPorId(Long id) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            return ResponseEntity.ok().body(usuario);
        } catch (IllegalArgumentException e) {
            String mensagemErro = "Erro ao buscar usuário por ID: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }
    }

    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            String mensagem = "Não foi possível excluir o usuário: usuário não encontrado";
            Util.escreveLog(mensagem);
            throw new IllegalArgumentException(mensagem);
        }
        Util.escreveLog("Excluindo usuario ...");
        usuarioRepository.deleteById(id);
        Util.escreveLog("Usuario excluído com sucesso");
    }

    public ResponseEntity<?> atualizar(Usuario usuario) {
        try {
            Optional<Usuario> usuario1 = usuarioRepository.findByEmail(usuario.getEmail());
            if (usuario1.isPresent() && !usuario1.get().getId().equals(usuario.getId())) {
                Util.escreveLog("Erro ao atualizar usuario: Já existe um usuario cadastrado com este e-mail");
                throw new IllegalArgumentException("Já existe um usuario cadastrado com este e-mail");
            }
            Util.escreveLog("Atualizando usuario ...");
            usuarioRepository.save(usuario);
            Util.escreveLog("Usuario atualizado com sucesso: "+usuario);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            Util.escreveLog("Erro ao atualizar usuario: " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
