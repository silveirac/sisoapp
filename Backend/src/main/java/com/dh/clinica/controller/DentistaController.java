package com.dh.clinica.controller;

import com.dh.clinica.model.Dentista;
import com.dh.clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @PostMapping
    public Dentista criarDentista(@RequestBody Dentista dentista) {
        return dentistaService.cadastrar(dentista);
    }

    @GetMapping
    public Collection<Dentista> listarTodos() {
        return dentistaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Dentista> buscarPorId(@PathVariable Integer id) {
        return dentistaService.buscarPorID(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        dentistaService.excluir(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Dentista dentistaDTO) {
        Optional<Dentista> optionalDentista = dentistaService.buscarPorID(id);
        if (optionalDentista.isEmpty()) {
            Mensagem mensagem = new Mensagem("Dentista não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        Dentista dentista = optionalDentista.get();
        dentista.setNome(dentistaDTO.getNome());
        dentista.setMatricula(dentistaDTO.getMatricula());
        Dentista dentistaAtualizado = dentistaService.atualizar(dentista);
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
