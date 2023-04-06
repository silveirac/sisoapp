package com.dh.sisoapp.controller;

import com.dh.sisoapp.controller.dto.EnderecoResponse;
import com.dh.sisoapp.model.Endereco;
import com.dh.sisoapp.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarEndereco(@RequestBody Endereco endereco) {
        try {
            //enderecoService.salvar(endereco);
            return new ResponseEntity<>(endereco, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> listarEnderecos() {
        try {
            return new ResponseEntity<>(enderecoService.listar(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorID(@PathVariable Long id) {
        try {
            EnderecoResponse endereco = enderecoService.buscarPorId(id);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirEndereco(@PathVariable Long id) {
        try {
            enderecoService.excluir(id);
            return new ResponseEntity<>("Sucess", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Object> atualizarEndereco(@RequestBody Endereco endereco) {
        try {
            enderecoService.atualizar(endereco);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
