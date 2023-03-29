package com.dh.sisoapp.service;

import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void salvar(Dentista dentista) {
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
    }

    public List<Dentista> listar() {
        return dentistaRepository.findAll();
    }

    public Dentista buscarPorId(Long id) {
        Optional<Dentista> dentista = dentistaRepository.findById(id);

        if (dentista.isEmpty()) {
            throw new IllegalArgumentException("Dentista não encontrado");
        }

        return dentista.get();
    }

    public void excluir(Long id) {
        if (!dentistaRepository.existsById(id)) {
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        dentistaRepository.deleteById(id);
    }

    public void atualizar(Dentista dentista) {
        Optional<Dentista> dentistaExistente = dentistaRepository.findByEmail(dentista.getEmail());
        if (dentistaExistente.isPresent() && !dentistaExistente.get().getId().equals(dentista.getId())) {
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este e-mail");
        }
        Optional<Dentista> dentistaCpfExistente = dentistaRepository.findByCpf(dentista.getCpf());
        if (dentistaCpfExistente.isPresent() && !dentistaCpfExistente.get().getId().equals(dentista.getId())) {
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CPF");
        }
        Optional<Dentista> dentistaCroExistente = dentistaRepository.findByCro(dentista.getCro());
        if (dentistaCroExistente.isPresent() && !dentistaCroExistente.get().getId().equals(dentista.getId())) {
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CRO");
        }
        if (!dentistaRepository.existsById(dentista.getId())) {
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        dentistaRepository.save(dentista);
    }
}
