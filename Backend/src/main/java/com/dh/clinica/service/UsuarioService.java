package com.dh.clinica.service;

import com.dh.clinica.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private IDao<Usuario> usuarioDao;

    public UsuarioService(IDao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioDao.salvar(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioDao.buscarTodos();
    }

    public Optional<Usuario> buscarPorId(Integer id) throws IOException {
        return usuarioDao.buscaPorId(id);
    }

    public void excluir(Integer id) throws IOException {
        usuarioDao.excluirID(id);
    }

    public Usuario atualizar(Usuario usuario) throws IOException {
        return usuarioDao.atualizar(usuario);
    }
}
