package com.dh.sisoapp.repository;

import com.dh.sisoapp.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

    Optional<Dentista> findByEmail(String email);

    Optional<Dentista> findByCpf(String cpf);

    Optional<Dentista> findByCro(String cro);

    boolean existsByEmail(String email);
}

