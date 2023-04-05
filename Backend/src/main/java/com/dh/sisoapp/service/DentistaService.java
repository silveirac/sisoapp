package com.dh.sisoapp.service;

import Util.Util;
import com.dh.sisoapp.controller.dto.DentistaResponse;
import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.repository.DentistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {
    private final DentistaRepository dentistaRepository;
    @Autowired
    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    public Dentista salvar(Dentista dentista) {
        if (dentistaRepository.existsByEmail(dentista.getEmail())) {
            Util.escreveLog("Erro ao cadastrar dentista: Já existe um dentista cadastrado com este e-mail");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este e-mail");
        }
        if (dentistaRepository.findByCpf(dentista.getCpf()).isPresent()) {
            Util.escreveLog("Erro ao cadastrar dentista: Já existe um dentista cadastrado com este CPF");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CPF");
        }
        if (dentistaRepository.findByCro(dentista.getCro()).isPresent()) {
            Util.escreveLog("Erro ao cadastrar dentista: Já existe um dentista cadastrado com este CRO");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CRO");
        }
        Util.escreveLog("Dentista salvo com sucesso: "+dentista);
        return dentistaRepository.save(dentista);
    }

    public List<DentistaResponse> listar() {
        Util.escreveLog("Listando todos pacientes ...");
        List<Dentista> dentistas = dentistaRepository.findAll();
        List<DentistaResponse> dentistaResponses = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Dentista dentista: dentistas) {
            dentistaResponses.add(mapper.convertValue(dentista, DentistaResponse.class));
        }
        return dentistaResponses;
    }

    public DentistaResponse buscarPorId(Long id) {
        Optional<Dentista> dentista = dentistaRepository.findById(id);

        if (dentista.isEmpty()) {
            Util.escreveLog("Erro ao buscar por ID: Dentista não encontrada");
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        Util.escreveLog("Dentista encontrado na busca pelo ID: "+dentista);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(dentista.get(), DentistaResponse.class);
    }
    public void excluir(Long id) {
        if (!dentistaRepository.existsById(id)) {
            Util.escreveLog("Dentista não encontrada para exclusão");
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        dentistaRepository.deleteById(id);
        Util.escreveLog("Dentista ID "+id+" excluído com sucesso");
    }

    public void atualizar(Dentista dentista) {
        Optional<Dentista> dentistaExistente = dentistaRepository.findByEmail(dentista.getEmail());
        if (dentistaExistente.isPresent() && !dentistaExistente.get().getId().equals(dentista.getId())) {
            Util.escreveLog("Erro ao atualizar dentista: Já existe um dentista cadastrado com este e-mail");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este e-mail");
        }
        Optional<Dentista> dentistaCpfExistente = dentistaRepository.findByCpf(dentista.getCpf());
        if (dentistaCpfExistente.isPresent() && !dentistaCpfExistente.get().getId().equals(dentista.getId())) {
            Util.escreveLog("Erro ao atualizar dentista: Já existe um dentista cadastrado com este CPF");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CPF");
        }
        Optional<Dentista> dentistaCroExistente = dentistaRepository.findByCro(dentista.getCro());
        if (dentistaCroExistente.isPresent() && !dentistaCroExistente.get().getId().equals(dentista.getId())) {
            Util.escreveLog("Erro ao atualizar dentista: Já existe um dentista cadastrado com este CRO");
            throw new IllegalArgumentException("Já existe um dentista cadastrado com este CRO");
        }
        if (!dentistaRepository.existsById(dentista.getId())) {
            Util.escreveLog("Erro ao atualizar dentista: Dentista não encontrado");
            throw new IllegalArgumentException("Dentista não encontrado");
        }
        Util.escreveLog("Atualizando dentista ...");
        dentistaRepository.save(dentista);
        Util.escreveLog("Dentista atualizado com sucessso: "+dentista);
    }
}