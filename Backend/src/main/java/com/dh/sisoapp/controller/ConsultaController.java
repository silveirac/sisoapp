package com.dh.sisoapp.controller;

import com.dh.sisoapp.model.Consulta;
import com.dh.sisoapp.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarConsulta(@RequestBody Consulta consulta) {
        try {
            consultaService.salvar(consulta);
            return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarConsultas() {
        try {
            return new ResponseEntity<>(consultaService.listar(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarPorId(@PathVariable Long id) {
        try {
            Consulta consulta = consultaService.consultarPorId(id);
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirConsulta(@PathVariable Long id) {
        try {
            consultaService.excluir(id);
            return new ResponseEntity<>("Sucess", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> atualizarConsulta(@RequestBody Consulta consulta) {
        try {
            consultaService.atualizar(consulta);
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}