package com.dh.clinica.controller;

import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public Paciente salvaUsuario(@RequestBody Paciente usuario) {
        return pacienteService.cadastrar(usuario);
    }

    @GetMapping
    public List<Paciente> listaTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Paciente> buscaPaciente(@PathVariable Integer id) {
        return pacienteService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        pacienteService.excluir(id);
    }

    @PutMapping
    public Paciente atualizar(@RequestBody Paciente paciente) {
        return pacienteService.atualizar(paciente);
    }

}
