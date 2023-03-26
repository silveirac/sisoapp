package com.dh.clinica.service;

import com.dh.clinica.model.Usuaio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    private IDao<Usuaio> dentistaDao;

    public DentistaService(IDao<Usuaio> dentistaDao) {
        this.dentistaDao = dentistaDao;
    }

    public Usuaio cadastrar(Usuaio dentista) {
        return dentistaDao.salvar(dentista);
    }

    public List<Usuaio> listarTodos() {
        return dentistaDao.buscarTodos();
    }

    public Optional<Usuaio> buscarPorID(Integer id) {
        return dentistaDao.buscaPorId(id);
    }

    public void excluir(Integer id) {
        dentistaDao.excluirID(id);
    }

    public Usuaio atualizar(Usuaio dentista) {
        return dentistaDao.atualizar(dentista);
    }
}
