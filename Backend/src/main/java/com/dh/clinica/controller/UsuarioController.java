package com.dh.clinica.controller;

import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/salvar")
    public Usuario salvaUsuario(@RequestBody Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }

    @GetMapping("/buscar")
    public List<Usuario> listaTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/buscar/{id}")
    public Optional<Usuario> buscaUsuario(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        usuarioService.excluir(id);
    }

    @PutMapping
    public Usuario atualizar(@RequestBody Usuario usuario) {
        return usuarioService.atualizar(usuario);
    }
}
