package com.dh.clinica.service;

import com.dh.clinica.model.Consulta;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    private IDao<Consulta> consultaIDao;

    public ConsultaService(IDao<Consulta> consultaIDao) {
        this.consultaIDao = consultaIDao;
    }

    public Consulta cadastrar(Consulta consulta) {
        return consultaIDao.salvar(consulta);
    }

    public List<Consulta> listarTodos() {
        return consultaIDao.buscarTodos();
    }

    public Optional<Consulta> buscarPorId(Integer id) {
        return consultaIDao.buscaPorId(id);
    }

    public void excluir(Integer id) {
        consultaIDao.excluirID(id);
    }

    public Consulta atualizar(Consulta consulta) {
        return consultaIDao.atualizar(consulta);
    }
}
