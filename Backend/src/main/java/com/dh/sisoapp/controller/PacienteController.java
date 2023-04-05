package com.dh.sisoapp.controller;

import com.dh.sisoapp.controller.dto.PacienteResponse;
import com.dh.sisoapp.model.Paciente;
import com.dh.sisoapp.service.EnderecoService;
import com.dh.sisoapp.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;
    private final EnderecoService enderecoService;
    public PacienteController(PacienteService pacienteService, EnderecoService enderecoService) {
        this.pacienteService = pacienteService;
        this.enderecoService = enderecoService;
    }
    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente) {
        try {
            enderecoService.salvar(paciente.getEndereco());
            pacienteService.salvar(paciente);
            return new ResponseEntity<>(paciente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorID(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        try {
            pacienteService.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping()
    public ResponseEntity<Object> atualizarPaciente(@RequestBody Paciente paciente) {
        PacienteResponse pacienteAtual = pacienteService.buscarPorId(paciente.getId());
        if (pacienteAtual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pacienteService.atualizar(paciente);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }
}