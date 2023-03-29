package com.dh.sisoapp.service;

import com.dh.sisoapp.model.Consulta;
import com.dh.sisoapp.repository.ConsultaRepository;
import com.dh.sisoapp.repository.DentistaRepository;
import com.dh.sisoapp.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {


    private final ConsultaRepository consultaRepository;

    private final PacienteRepository pacienteRepository;

    private final DentistaRepository dentistaRepository;
    @Autowired
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
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        if (!dentistaRepository.existsById(consulta.getDentista().getId())) {
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        consultaRepository.save(consulta);
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
            throw new IllegalArgumentException("Consulta não encontrada");
        }
        consultaRepository.deleteById(id);
    }

    public void atualizar(Consulta consulta) {
        // Verifica se a consulta existe no sistema
        if (!consultaRepository.existsById(consulta.getId())) {
            throw new IllegalArgumentException("Consulta não encontrada");
        }

        // Verifica se paciente e dentista existem no sistema
        if (!pacienteRepository.existsById(consulta.getPaciente().getId())) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        if (!dentistaRepository.existsById(consulta.getDentista().getId())) {
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        consultaRepository.save(consulta);
    }
}
