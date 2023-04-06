package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.controller.dto.EnderecoRequest;
import com.dh.sisoapp.controller.dto.EnderecoResponse;
import com.dh.sisoapp.model.Endereco;
import com.dh.sisoapp.repository.IEnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void salvar(EnderecoRequest endereco){
        Util.escreveLog("Salvando endereco ...");
        ObjectMapper mapper = new ObjectMapper();

        enderecoRepository.save(mapper.convertValue(endereco, Endereco.class));
        Util.escreveLog("Endereço salvo com sucesso: "+endereco);
    }

    public List<Endereco> listar(){
        Util.escreveLog("Listando todos endereços ...");
        return enderecoRepository.findAll();
    }

    public EnderecoResponse buscarPorId(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            Util.escreveLog("Erro ao buscar endereço por ID: Endereço não encontrado");
            throw new IllegalArgumentException("Endereço não encontrado");
        }
        Util.escreveLog("Endereço encontrado com sucesso: "+endereco);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(endereco.get(), EnderecoResponse.class);
    }
    public void excluir(Long id) {
        if (!enderecoRepository.existsById(id)) {
            Util.escreveLog("Erro ao excluir endereço: Endereço não encontrado");
            throw new IllegalArgumentException("Endereço não encontrado");
        }
        Util.escreveLog("Excluindo endereço ...");
        enderecoRepository.deleteById(id);
        Util.escreveLog("Endereço ID "+id+" excluído com sucesso");
    }
    public void atualizar(Endereco endereco){
        Util.escreveLog("Atualizando endereço ...");
        enderecoRepository.save(endereco);
        Util.escreveLog("Endereço atualizado com sucesso: "+endereco);
    }
}