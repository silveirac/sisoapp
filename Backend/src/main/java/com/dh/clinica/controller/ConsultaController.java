package com.dh.clinica.controller;

import com.dh.clinica.model.Consulta;
import com.dh.clinica.service.ConsultaService;
import com.dh.clinica.service.DentistaService;
import com.dh.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<Consulta> cadastrar(@RequestBody Consulta consulta) {
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
    public ResponseEntity<List<Consulta>> buscarTodos() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<Consulta> atualizar(@RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.atualizar(consulta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Integer id) {
        ResponseEntity<String> response;
        if (consultaService.buscarPorId(id).isPresent()) {
            consultaService.excluir(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Consulta apagada!");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
