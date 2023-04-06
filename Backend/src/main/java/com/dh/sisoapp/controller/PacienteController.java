package com.dh.sisoapp.controller;

import com.dh.sisoapp.controller.dto.PacienteRequest;
import com.dh.sisoapp.controller.dto.PacienteResponse;
import com.dh.sisoapp.model.Paciente;
import com.dh.sisoapp.service.EnderecoService;
import com.dh.sisoapp.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> cadastrarPaciente(@RequestBody PacienteRequest paciente) {
        try {
            //enderecoService.salvar(paciente.getEndereco());
            pacienteService.salvar(paciente);
            return new ResponseEntity<>(paciente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarPacientes() {
        try {
            return new ResponseEntity<>(pacienteService.listar(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorID(@PathVariable Long id){
        try {
            PacienteResponse paciente = pacienteService.buscarPorId(id);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPaciente(@PathVariable Long id) {
        try {
            pacienteService.excluir(id);
            return new ResponseEntity<>("Sucess", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPaciente(@PathVariable Long id,@RequestBody PacienteRequest paciente) {
        try {
            pacienteService.atualizar(id,paciente);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}