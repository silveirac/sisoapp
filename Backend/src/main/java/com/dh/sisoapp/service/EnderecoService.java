package com.dh.sisoapp.service;

import com.dh.sisoapp.model.Endereco;
import com.dh.sisoapp.repository.IEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private IEnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(IEnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void salvar(Endereco endereco){
        enderecoRepository.save(endereco);
    }

    public List<Endereco> listar(){
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            throw new IllegalArgumentException("Endereço não encontrado");
        }
        return endereco.get();
    }
    public void excluir(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new IllegalArgumentException("Endereço não encontrado");
        }
        enderecoRepository.deleteById(id);
    }
    public void atualizar(Endereco endereco){
        enderecoRepository.save(endereco);
    }
}
