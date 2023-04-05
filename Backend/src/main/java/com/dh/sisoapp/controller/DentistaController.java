package com.dh.sisoapp.controller;

import com.dh.sisoapp.controller.dto.DentistaResponse;
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
    public ResponseEntity<List<DentistaResponse>> listarDentistas() {
        return ResponseEntity.ok(dentistaService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DentistaResponse> buscarPorID(@PathVariable Long id){
        return ResponseEntity.ok(dentistaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDentista(@PathVariable Long id) {
        try {
            dentistaService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping()
    public ResponseEntity<Void> atualizarDentista(@RequestBody Dentista dentista) {
        try {
            dentistaService.atualizar(dentista);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}