package com.dh.clinica.controller;

import com.dh.clinica.model.Dentista;
import com.dh.clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {
    @Autowired
    private DentistaService dentistaService;

    @PostMapping("/salvar")
    public Dentista salvaUsuario(@RequestBody Dentista dentista){
        return dentistaService.cadastrar(dentista);
    }

    @GetMapping("/buscar")
    public List<Dentista> listaTodos(){
        return dentistaService.listarTodos();
    }
    @GetMapping("/buscar/{id}")
    public Optional<Dentista> buscaDentista(@PathVariable Integer id){
        return dentistaService.buscarPorID(id);
    }
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id){
        dentistaService.excluir(id);
    }

    @PutMapping
    public Dentista atualizar(@RequestBody Dentista dentista){
        return dentistaService.atualizar(dentista);
    }
}
