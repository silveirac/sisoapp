package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.controller.dto.PacienteRequest;
import com.dh.sisoapp.controller.dto.PacienteResponse;
import com.dh.sisoapp.model.Paciente;
import com.dh.sisoapp.repository.IEnderecoRepository;
import com.dh.sisoapp.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, IEnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteResponse salvar(PacienteRequest paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());
        if (pacienteExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
        }
        Util.escreveLog("Paciente salvo: "+paciente);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        pacienteRepository.save(mapper.convertValue(paciente, Paciente.class));
        return  mapper.convertValue(paciente, PacienteResponse.class);
    }

    public void atualizar(Long id,PacienteRequest pacienteRequest) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(pacienteRequest.getCpf());
        ObjectMapper mapper =new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Paciente paciente = mapper.convertValue(pacienteRequest, Paciente.class);
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

    public List<PacienteResponse> listar() {
        Util.escreveLog("Listando todos pacientes...");
        ObjectMapper mapper = new ObjectMapper();
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteResponse> pacienteResponses = new ArrayList<>();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        for(Paciente paciente: pacientes){
            pacienteResponses.add(mapper.convertValue(paciente, PacienteResponse.class));
        }
        return pacienteResponses;
    }

    public PacienteResponse buscarPorId(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            Util.escreveLog("Paciente não encontrado");
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        Util.escreveLog("Paciente encontrado na busca pelo ID: "+paciente);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.convertValue(paciente.get(), PacienteResponse.class);
    }
}