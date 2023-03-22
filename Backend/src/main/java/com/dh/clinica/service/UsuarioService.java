package com.dh.clinica.service;

import com.dh.clinica.model.Usuario;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.impl.UsuarioDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private IDao<Usuario> usuarioDao;

    public UsuarioService(IDao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Usuario cadastrar(Usuario usuario){
        return usuarioDao.salvar(usuario);
    }
    public List<Usuario> listarTodos(){
        return usuarioDao.buscarTodos();
    }

    public Optional<Usuario> buscarPorId(Integer id){
        return usuarioDao.buscaPorId(id);
    }

    public void excluir(Integer id){
        usuarioDao.excluirID(id);
    }
    public Usuario atualizar(Usuario usuario){
        return usuarioDao.atualizar(usuario);
    }
}
