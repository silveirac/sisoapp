package com.dh.clinica.controller;

import com.dh.clinica.model.Consulta;
import com.dh.clinica.service.ConsultaService;
import com.dh.clinica.service.DentistaService;
import com.dh.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private DentistaService dentistaService;
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta) {
        ResponseEntity<Consulta> response;
        if (pacienteService.buscarPorId(consulta.getPaciente().getId()).isPresent() &&
                dentistaService.buscarPorID(consulta.getDentista().getId()).isPresent()) {
            response = ResponseEntity.ok(consultaService.cadastrar(consulta));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> buscarTodasConsultas() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable Integer id, @RequestBody Consulta consulta) {
        Optional<Consulta> consultaExistente = consultaService.buscarPorId(id);
        if (consultaExistente.isPresent()) {
            consulta.setId(id);
            return ResponseEntity.ok(consultaService.atualizar(consulta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Integer id) {
        Optional<Consulta> consultaExistente = consultaService.buscarPorId(id);
        if (consultaExistente.isPresent()) {
            consultaService.excluir(id);
            return ResponseEntity.ok("Consulta apagada!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
