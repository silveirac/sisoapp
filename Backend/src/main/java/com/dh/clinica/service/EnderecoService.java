package com.dh.clinica.service;

import com.dh.clinica.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private IDao<Endereco> enderecoDao;

    public EnderecoService(IDao<Endereco> enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    public Endereco cadastrar(Endereco endereco) {
        return enderecoDao.salvar(endereco);
    }

    public List<Endereco> listarTodos() {
        return enderecoDao.buscarTodos();
    }

    public Optional<Endereco> buscarPorId(Integer id) throws IOException {
        return enderecoDao.buscaPorId(id);
    }

    public void excluir(Integer id) throws IOException {
        enderecoDao.excluirID(id);
    }
}
