package com.dh.sisoapp.service;

import Util.Util;
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

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, IEnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente salvar(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());
        if (pacienteExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
        }
        Util.escreveLog("Paciente salvo: "+paciente);
        return  pacienteRepository.save(paciente);
    }

    public void atualizar(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());

        if (pacienteExistente.isPresent() && !pacienteExistente.get().getId().equals(paciente.getId())) {
            Util.escreveLog("Já existe um paciente cadastrado com esse CPF");
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
        }
        Util.escreveLog("Atualizando paciente ...");
        pacienteRepository.save(paciente);
        Util.escreveLog("Paciente atualizado com sucesso: "+paciente);
    }

    public void excluir(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            Util.escreveLog("Paciente não encontrado para exclusão");
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        pacienteRepository.delete(paciente.get());
        Util.escreveLog("Paciente ID "+id+" excluído com sucesso");
    }

    public List<Paciente> listar() {
        Util.escreveLog("Listando todos pacientes...");
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            Util.escreveLog("Paciente não encontrado");
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        Util.escreveLog("Paciente encontrado na busca pelo ID: "+paciente);
        return paciente.get();
    }
}