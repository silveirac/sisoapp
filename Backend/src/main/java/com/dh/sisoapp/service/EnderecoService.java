package com.dh.sisoapp.service;

import Util.Util;
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
        Util.escreveLog("Salvando endereco ...");
        enderecoRepository.save(endereco);
        Util.escreveLog("Endereço salvo com sucesso: "+endereco);
    }

    public List<Endereco> listar(){
        Util.escreveLog("Listando todos endereços ...");
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isEmpty()) {
            Util.escreveLog("Erro ao buscar endereço por ID: Endereço não encontrado");
            throw new IllegalArgumentException("Endereço não encontrado");
        }
        Util.escreveLog("Endereço encontrado com sucesso: "+endereco);
        return endereco.get();
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
