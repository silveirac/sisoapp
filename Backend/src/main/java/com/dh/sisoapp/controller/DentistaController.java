package com.dh.sisoapp.controller;

import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {
    private final DentistaService dentistaService;

    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping
    public ResponseEntity<Dentista> cadastrarDentista(@RequestBody Dentista dentista) {
        try {
            dentistaService.salvar(dentista);
            return new ResponseEntity<>(dentista, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Dentista>> listarDentistas() {
        List<Dentista> dentistas = dentistaService.listar();
        return ResponseEntity.ok(dentistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(dentistaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dentista> excluirDentista(@PathVariable Long id) {
        try {
            dentistaService.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Dentista> atualizarDentista(@RequestBody Dentista dentista) {
        try {
            dentistaService.atualizar(dentista);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
