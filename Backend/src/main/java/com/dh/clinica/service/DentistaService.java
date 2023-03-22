package com.dh.clinica.service;

import com.dh.clinica.model.Dentista;
import com.dh.clinica.service.impl.DentistaDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    @Autowired
    private IDao<Dentista> dentistaDao;
    public DentistaService(IDao<Dentista> dentistaDao){
        this.dentistaDao = dentistaDao;
    }

    public Dentista cadastrar(Dentista dentista){
        return dentistaDao.salvar(dentista);
    }
    public List<Dentista> listarTodos(){
        return dentistaDao.buscarTodos();
    }

    public Optional<Dentista> buscarPorID(Integer id){
        return dentistaDao.buscaPorId(id);
    }
    public void excluir(Integer id){
        dentistaDao.excluirID(id);
    }
    public Dentista atualizar(Dentista dentista){
        return dentistaDao.atualizar(dentista);
    }
}
