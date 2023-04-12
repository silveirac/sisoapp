package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.controller.dto.UsuarioLoginRequest;
import com.dh.sisoapp.controller.dto.UsuarioRequest;
import com.dh.sisoapp.controller.dto.UsuarioResponse;
import com.dh.sisoapp.model.Usuario;
import com.dh.sisoapp.repository.IUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public UsuarioResponse salvar(UsuarioRequest usuarioRequest){
        if (usuarioRepository.existsByEmail(usuarioRequest.getEmail())) {
            Util.escreveLog("Erro ao cadastrar usuário: Já existe um usuario cadastrado com este e-mail");
            throw new IllegalArgumentException("Já existe um usuario cadastrado com este e-mail");
        }
        Util.escreveLog("Salvando usuario ...");
        ObjectMapper mapper = new ObjectMapper();
        Usuario usuario = mapper.convertValue(usuarioRequest, Usuario.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);
        Util.escreveLog("Usuario salvo com successo: "+usuarioRequest);
        return mapper.convertValue(usuarioRequest, UsuarioResponse.class);
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
    public void atualizar(Long id,UsuarioRequest usuario){
        Optional<Usuario> usuario1 = usuarioRepository.findByEmail(usuario.getEmail());
        ObjectMapper mapper = new ObjectMapper();
        Usuario usuario2 =  mapper.convertValue(usuario, Usuario.class);
        usuario2.setId(id);
        if (usuario1.isPresent() && !usuario1.get().getId().equals(usuario2.getId())) {
            Util.escreveLog("Erro ao atualizar usuario: Já existe um usuario cadastrado com este e-mail");
            throw new IllegalArgumentException("Já existe um usuario cadastrado com este e-mail");
        }
        Util.escreveLog("Atualizando usuario ...");
        usuarioRepository.save(usuario2);
        Util.escreveLog("Usuario atualizado com sucesso: "+usuario);
    }


}