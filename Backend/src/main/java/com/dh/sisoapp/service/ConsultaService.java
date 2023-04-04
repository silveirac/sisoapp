package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.model.Consulta;
import com.dh.sisoapp.repository.ConsultaRepository;
import com.dh.sisoapp.repository.DentistaRepository;
import com.dh.sisoapp.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> salvar(Consulta consulta) {
        // Verifica se paciente e dentista existem no sistema
        if (!pacienteRepository.existsById(consulta.getPaciente().getId())) {
            Util.escreveLog("Paciente não encontrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente não encontrado");
        }
        if (!dentistaRepository.existsById(consulta.getDentista().getId())) {
            Util.escreveLog("Dentista não encontrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dentista não encontrado");
        }
        Util.escreveLog("Salvando consulta: "+ consulta);
        consultaRepository.save(consulta);
        Util.escreveLog("Consulta salva com sucesso: "+consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(consulta));
    }
    public ResponseEntity<?> listar() {
        List<Consulta> consultas = consultaRepository.findAll();
        if (consultas.isEmpty()) {
            Util.escreveLog("Não existem consultas cadastradas");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não existem consultas cadastradas");
        } else {
            return ResponseEntity.ok(consultas);
        }
    }
    public ResponseEntity<?> consultarPorId(Long id) {
        try {
            Consulta consulta = consultaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
            return ResponseEntity.ok(consulta);
        } catch (IllegalArgumentException e) {
            Util.escreveLog("Consulta não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
        }
    }
    public ResponseEntity<?> excluir(Long id) {
        try {
            if (!consultaRepository.existsById(id)) {
                Util.escreveLog("Consulta não encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
            }
            consultaRepository.deleteById(id);
            Util.escreveLog("Consulta excluída com sucesso");
            return ResponseEntity.ok("Consulta excluída com sucesso");
        } catch (Exception e) {
            Util.escreveLog("Erro ao excluir consulta: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir consulta");
        }
    }

    public ResponseEntity<?> atualizar(Consulta consulta) {
        try {
            // Verifica se a consulta existe no sistema
            if (!consultaRepository.existsById(consulta.getId())) {
                Util.escreveLog("Consulta não encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
            }

            // Verifica se paciente e dentista existem no sistema
            if (!pacienteRepository.existsById(consulta.getPaciente().getId())) {
                Util.escreveLog("Paciente não encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
            }
            if (!dentistaRepository.existsById(consulta.getDentista().getId())) {
                Util.escreveLog("Dentista não encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentista não encontrado");
            }

            consultaRepository.save(consulta);
            Util.escreveLog("Consulta atualizada com sucesso");
            return ResponseEntity.ok("Consulta atualizada com sucesso");
        } catch (Exception e) {
            Util.escreveLog("Erro ao atualizar consulta: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar consulta");
        }
    }

}
