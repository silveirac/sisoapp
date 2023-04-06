package com.dh.sisoapp.repository;

import com.dh.sisoapp.model.Consulta;
import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByDentista(Dentista dentista);
    List<Consulta> findByPaciente(Paciente paciente);
    Optional<Consulta> findByIdAndDentista(Long id, Dentista dentista);
    List<Consulta> findByDentistaId(Long dentistaId);
}
