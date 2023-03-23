package com.dh.clinica.service.impl;

import com.dh.clinica.model.Consulta;
import com.dh.clinica.service.IDao;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IDao<Consulta> {
    private List<Consulta> consultas;

    public ConsultaServiceImpl(List<Consulta> consultas) {
        this.consultas = new ArrayList<>();
    }

    @Override
    public Consulta salvar(Consulta consulta) {
        this.consultas.add(consulta);
        return consulta;
    }

    @Override
    public List<Consulta> buscarTodos() {
        return consultas;
    }

    @Override
    public Optional<Consulta> buscaPorId(Integer id) {
        return consultas.stream().filter(consulta -> consulta.getId().equals(id)).findFirst();
    }

    @Override
    public void excluirID(Integer id) {
        consultas.removeIf(consulta -> consulta.getId().equals(id));
    }

    @Override
    public Consulta atualizar(Consulta consulta) {
        excluirID(consulta.getId());
        consultas.add(consulta);
        return consulta;
    }

}
