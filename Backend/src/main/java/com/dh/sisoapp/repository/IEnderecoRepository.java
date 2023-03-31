package com.dh.sisoapp.repository;

import com.dh.sisoapp.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco,Long> {
}
