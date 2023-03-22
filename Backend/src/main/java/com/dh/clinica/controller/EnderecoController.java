//package com.dh.clinica.controller;
//
//import com.dh.clinica.model.Dentista;
//import com.dh.clinica.model.Endereco;
//import com.dh.clinica.model.Endereco;
//import com.dh.clinica.service.EnderecoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/enderecos")
//public class EnderecoController {
//    @Autowired
//    private EnderecoService enderecoService;
//
//    @PostMapping("/salvar")
//    public Endereco salvaUsuario(@RequestBody Endereco endereco){
//        return enderecoService.cadastrar(endereco);
//    }
//
//    @GetMapping("/buscar")
//    public List<Endereco> listaTodos(){
//        return enderecoService.listarTodos();
//    }
//
//    @GetMapping("/buscar/{id}")
//    public Endereco buscaEndereco(@PathVariable Integer id){
//        return enderecoService.buscarPorId(id);
//    }
//    @DeleteMapping("/{id}")
//    public void excluir(@PathVariable Integer id){
//        enderecoService.excluir(id);
//    }
//}
