package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.model.Consulta;
import com.dh.sisoapp.repository.ConsultaRepository;
import com.dh.sisoapp.repository.DentistaRepository;
import com.dh.sisoapp.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final DentistaRepository dentistaRepository;
    public ConsultaService(ConsultaRepository consultaRepository,
                           PacienteRepository pacienteRepository,
                           DentistaRepository dentistaRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.dentistaRepository = dentistaRepository;
    }

    public void salvar(Consulta consulta) {
        // Verifica se paciente e dentista existem no sistema
        if (!pacienteRepository.existsById(consulta.getPaciente().getId())) {
            Util.escreveLog("Paciente não encontrado");
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        if (!dentistaRepository.existsById(consulta.getDentista().getId())) {
            Util.escreveLog("Dentista não encontrado");
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        Util.escreveLog("Salvando consulta: "+ consulta);
        consultaRepository.save(consulta);
        Util.escreveLog("Consulta salva com sucesso: "+consulta);
    }

    public List<Consulta> listar() {
        return consultaRepository.findAll();
    }
    public Consulta consultarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
    }

    public void excluir(Long id) {
        if (!consultaRepository.existsById(id)) {
            Util.escreveLog("Consulta não encontrada para exclusão");
            throw new IllegalArgumentException("Consulta não encontrado");
        }
        consultaRepository.deleteById(id);
        Util.escreveLog("Consulta ID "+id+" excluído com sucesso");
    }

    public void atualizar(Consulta consulta) {
        // Verifica se a consulta existe no sistema
        if (!consultaRepository.existsById(consulta.getId())) {
            Util.escreveLog("Consulta não encontrada");
            throw new IllegalArgumentException("Consulta não encontrada");
        }

        // Verifica se paciente e dentista existem no sistema
        if (!pacienteRepository.existsById(consulta.getPaciente().getId())) {
            Util.escreveLog("Paciente não encontrado");
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        if (!dentistaRepository.existsById(consulta.getDentista().getId())) {
            Util.escreveLog("Dentista não encontrada");
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        consultaRepository.save(consulta);
    }

}