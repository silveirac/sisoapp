package com.dh.sisoapp.controller;

import com.dh.sisoapp.model.Consulta;
import com.dh.sisoapp.model.Paciente;
import com.dh.sisoapp.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta) {
        try {
            consultaService.salvar(consulta);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Consulta> listarConsultas() {
        return consultaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> consultarPorId(@PathVariable Long id) {
        try {
            Consulta consulta = consultaService.consultarPorId(id);
            return ResponseEntity.ok(consulta);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Consulta> excluirConsulta(@PathVariable Long id) {
        try {
            consultaService.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Consulta> atualizarConsulta(@RequestBody Consulta consulta) {
        try {
            consultaService.atualizar(consulta);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
