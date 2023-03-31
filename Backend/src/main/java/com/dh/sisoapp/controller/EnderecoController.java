package com.dh.sisoapp.controller;

import com.dh.sisoapp.model.Endereco;
import com.dh.sisoapp.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private EnderecoService enderecoService;
    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco){
        try {
            enderecoService.salvar(endereco);
            return new ResponseEntity<>(endereco, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos(){
        return ResponseEntity.ok(enderecoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorID(@PathVariable Long id){
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> excluirEndereco(@PathVariable Long id){
        try {
            enderecoService.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Endereco> atualizarEndereco(@RequestBody Endereco endereco){
        try {
            enderecoService.atualizar(endereco);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
