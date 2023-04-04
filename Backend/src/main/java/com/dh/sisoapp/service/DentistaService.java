package com.dh.sisoapp.service;

import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {


    private final DentistaRepository dentistaRepository;

    @Autowired
    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    public ResponseEntity<?> salvar(Dentista dentista) {
        try {
            if (dentistaRepository.existsByEmail(dentista.getEmail())) {
                throw new IllegalArgumentException("Já existe um dentista cadastrado com este e-mail");
            }
            if (dentistaRepository.findByCpf(dentista.getCpf()).isPresent()) {
                throw new IllegalArgumentException("Já existe um dentista cadastrado com este CPF");
            }
            if (dentistaRepository.findByCro(dentista.getCro()).isPresent()) {
                throw new IllegalArgumentException("Já existe um dentista cadastrado com este CRO");
            }
            dentistaRepository.save(dentista);
            return ResponseEntity.ok(dentista);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<List<Dentista>> listar() {
        List<Dentista> dentistas = dentistaRepository.findAll();
        if (dentistas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dentistas);
    }


    public ResponseEntity<?> buscarPorId(Long id) {
        Optional<Dentista> dentista = dentistaRepository.findById(id);

        if (dentista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dentista.get());
    }

    public ResponseEntity<String> excluir(Long id) {
        if (!dentistaRepository.existsById(id)) {
            Util.escreveLog("Dentista não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentista não encontrado");
        }
        dentistaRepository.deleteById(id);
        Util.escreveLog("Dentista excluído com sucesso");
        return ResponseEntity.ok("Dentista excluído com sucesso");
    }

    public ResponseEntity<Dentista> atualizar(Dentista dentista) {
        Optional<Dentista> dentistaExistente = dentistaRepository.findByEmail(dentista.getEmail());
        if (dentistaExistente.isPresent() && !dentistaExistente.get().getId().equals(dentista.getId())) {
            Util.escreveLog("Já existe um dentista cadastrado com este e-mail");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este e-mail");
        }
        Optional<Dentista> dentistaCpfExistente = dentistaRepository.findByCpf(dentista.getCpf());
        if (dentistaCpfExistente.isPresent() && !dentistaCpfExistente.get().getId().equals(dentista.getId())) {
            Util.escreveLog("Já existe um dentista cadastrado com este CPF");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CPF");
        }
        Optional<Dentista> dentistaCroExistente = dentistaRepository.findByCro(dentista.getCro());
        if (dentistaCroExistente.isPresent() && !dentistaCroExistente.get().getId().equals(dentista.getId())) {
            Util.escreveLog("Já existe um dentista cadastrado com este CRO");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CRO");
        }
        Optional<Dentista> dentistaAntigo = dentistaRepository.findById(dentista.getId());
        if (dentistaAntigo.isEmpty()) {
            Util.escreveLog("Dentista não encontrado");
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        Dentista dentistaAtualizado = dentistaRepository.save(dentista);
        Util.escreveLog("Dentista Atualizado com Sucesso");
        return ResponseEntity.ok(dentistaAtualizado);
    }

}
