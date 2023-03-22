package com.dh.clinica.service;

import com.dh.clinica.model.Endereco;
import com.dh.clinica.model.Usuario;
import com.dh.clinica.service.impl.EnderecoDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private IDao<Endereco> enderecoDao;

    public EnderecoService(IDao<Endereco> enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    public Endereco cadastrar(Endereco endereco){
        return enderecoDao.salvar(endereco);
    }
    public List<Endereco> listarTodos(){
        return enderecoDao.buscarTodos();
    }

    public Optional<Endereco> buscarPorId(Integer id){
        return enderecoDao.buscaPorId(id);
    }
    public void excluir(Integer id){
        enderecoDao.excluirID(id);
    }
}
