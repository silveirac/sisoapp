package com.dh.sisoapp.service;

import com.dh.sisoapp.model.Usuario;
import com.dh.sisoapp.model.Usuario;
import com.dh.sisoapp.model.Usuario;
import com.dh.sisoapp.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private IUsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void salvar(Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuario cadastrado com este e-mail");
        }
        usuarioRepository.save(usuario);
    }
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        return usuario.get();
    }
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
    public void atualizar(Usuario usuario){
        Optional<Usuario> usuario1 = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuario1.isPresent() && !usuario1.get().getId().equals(usuario.getId())) {
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este e-mail");
        }
        usuarioRepository.save(usuario);
    }
}
