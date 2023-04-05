package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.controller.dto.UsuarioResponse;
import com.dh.sisoapp.model.Usuario;
import com.dh.sisoapp.repository.IUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            Util.escreveLog("Erro ao cadastrar usuário: Já existe um usuario cadastrado com este e-mail");
            throw new IllegalArgumentException("Já existe um usuario cadastrado com este e-mail");
        }
        Util.escreveLog("Salvando usuario ...");
        usuarioRepository.save(usuario);
        Util.escreveLog("Usuario salvo com successo: "+usuario);
    }
    public List<UsuarioResponse> listar(){
        Util.escreveLog("Listando todos usuarios ...");
        ObjectMapper mapper = new ObjectMapper();
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for(Usuario usuario: usuarios){
            usuarioResponses.add(mapper.convertValue(usuario, UsuarioResponse.class));
        }
        return usuarioResponses;
    }
    public UsuarioResponse buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            Util.escreveLog("Erro ao buscar usuario por ID: Usuario não encontrado");
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        Util.escreveLog("Usuario encontrado na busca por ID: "+usuario);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(usuario.get(), UsuarioResponse.class);
    }
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            Util.escreveLog("Erro na exclusão de usuario: Usuario não encontrado");
            throw new IllegalArgumentException("Usuario não encontrado");
        }
        Util.escreveLog("Excluindo usuario ...");
        usuarioRepository.deleteById(id);
        Util.escreveLog("Usuario excluído com sucesso");
    }
    public void atualizar(Usuario usuario){
        Optional<Usuario> usuario1 = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuario1.isPresent() && !usuario1.get().getId().equals(usuario.getId())) {
            Util.escreveLog("Erro ao atualizar usuario: Já existe um usuario cadastrado com este e-mail");
            throw new IllegalArgumentException("Já existe um usuario cadastrado com este e-mail");
        }
        Util.escreveLog("Atualizando usuario ...");
        usuarioRepository.save(usuario);
        Util.escreveLog("Usuario atualizado com sucesso: "+usuario);
    }
}