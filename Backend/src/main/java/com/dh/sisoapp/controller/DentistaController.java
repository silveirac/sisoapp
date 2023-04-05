package com.dh.sisoapp.controller;

import com.dh.sisoapp.controller.dto.DentistaResponse;
import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {
    private final DentistaService dentistaService;

    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarDentista(@RequestBody Dentista dentista) {
        try {
            dentistaService.salvar(dentista);
            return new ResponseEntity<>(dentista, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarDentistas() {
        try {
            return new ResponseEntity<>(dentistaService.listar(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorID(@PathVariable Long id) {
        try {
            DentistaResponse dentista = dentistaService.buscarPorId(id);
            return new ResponseEntity<>(dentista, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirDentista(@PathVariable Long id) {
        try {
            dentistaService.excluir(id);
            return new ResponseEntity<>("Sucess", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> atualizarDentista(@RequestBody Dentista dentista) {
        try {
            dentistaService.atualizar(dentista);
            return new ResponseEntity<>(dentista, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}