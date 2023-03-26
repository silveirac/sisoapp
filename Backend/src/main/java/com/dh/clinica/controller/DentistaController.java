package com.dh.clinica.controller;

import com.dh.clinica.model.Usuaio;
import com.dh.clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @PostMapping
    public Usuaio criarDentista(@RequestBody Usuaio dentista) {
        return dentistaService.cadastrar(dentista);
    }

    @GetMapping
    public Collection<Usuaio> listarTodos() {
        return dentistaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Usuaio> buscarPorId(@PathVariable Integer id) throws IOException {
        return dentistaService.buscarPorID(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) throws IOException {
        dentistaService.excluir(id);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody Usuaio dentistaDTO) throws IOException {
        Optional<Usuaio> optionalDentista = dentistaService.buscarPorID(dentistaDTO.getId());
        if (optionalDentista.isEmpty()) {
            Mensagem mensagem = new Mensagem("Dentista não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        Usuaio dentista = optionalDentista.get();
        dentista.setNome(dentistaDTO.getNome());
        dentista.setMatricula(dentistaDTO.getMatricula());
        Usuaio dentistaAtualizado = dentistaService.atualizar(dentista);
        return ResponseEntity.ok().build();
    }
    
    public class Mensagem {
        private String mensagem;
    
        public Mensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    
        public String getMensagem() {
            return mensagem;
        }
    
        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }
    

}
