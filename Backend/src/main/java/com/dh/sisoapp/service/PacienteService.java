package com.dh.sisoapp.service;

import com.dh.sisoapp.model.Paciente;
import com.dh.sisoapp.repository.IEnderecoRepository;
import com.dh.sisoapp.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final IEnderecoRepository enderecoRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, IEnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public void salvar(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());

        if (pacienteExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
        }

        pacienteRepository.save(paciente);
    }

    public void atualizar(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());

        if (pacienteExistente.isPresent() && !pacienteExistente.get().getId().equals(paciente.getId())) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
        }

        pacienteRepository.save(paciente);
    }

    public void excluir(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        pacienteRepository.delete(paciente.get());
    }

    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        return paciente.get();
    }
}