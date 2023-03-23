package com.dh.clinica.controller;

import com.dh.clinica.model.Dentista;
import com.dh.clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {
    
    private static final String DENTISTA_NAO_ENCONTRADO = "Dentista não encontrado";
    
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
    public Dentista atualizar(@PathVariable Integer id, @RequestBody Dentista dentistaAtualizado) {
        Optional<Dentista> dentista = dentistaService.buscarPorID(id);
        if (dentista.isEmpty()) {
            throw new DentistaNaoEncontradoException(DENTISTA_NAO_ENCONTRADO);
        }
        Dentista dentistaExistente = dentista.get();
        if (dentistaAtualizado.getNome() != null) {
            dentistaExistente.setNome(dentistaAtualizado.getNome());
        }
        if (dentistaAtualizado.getMatricula() != null) {
            dentistaExistente.setMatricula(dentistaAtualizado.getMatricula());
        }
        return dentistaService.atualizar(dentistaExistente);
    }
    public class DentistaNaoEncontradoException extends RuntimeException {
        public DentistaNaoEncontradoException(String message) {
            super(message);
        }
    }
    
}


