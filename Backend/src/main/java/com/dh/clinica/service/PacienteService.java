package com.dh.clinica.service;

import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.impl.PacienteDaoImpl;
import com.dh.clinica.service.impl.UsuarioDaoImpl;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private IDao<Paciente> pacienteDao;

    public PacienteService(IDao<Paciente> pacienteDao) {
        this.pacienteDao = pacienteDao;
    }

    public Paciente cadastrar(Paciente paciente){
        return pacienteDao.salvar(paciente);
    }
    public List<Paciente> listarTodos(){
        return pacienteDao.buscarTodos();
    }
    public Optional<Paciente> buscarPorId(Integer id){
        return pacienteDao.buscaPorId(id);
    }
    public void excluir(Integer id){
        pacienteDao.excluirID(id);
    }
    public Paciente atualizar(Paciente paciente){
        return pacienteDao.atualizar(paciente);
    }
}
