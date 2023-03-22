package com.dh.clinica.service;

import java.util.List;
import java.util.Optional;

public interface IDao<Object>{
    Object salvar(Object O);
    List<Object> buscarTodos();
    Optional<Object> buscaPorId(Integer id);
    void excluirID(Integer id);

    Object atualizar(Object o);
}
