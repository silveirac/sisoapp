package com.dh.sisoapp.service;

import com.dh.sisoapp.model.Paciente;
import com.dh.sisoapp.repository.IEnderecoRepository;
import com.dh.sisoapp.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    public Paciente salvar(Paciente paciente) {
        try {
            Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());
            if (pacienteExistente.isPresent()) {
                throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
            }
            pacienteRepository.save(paciente);
            return paciente;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public void atualizar(Paciente paciente) {
        try {
            Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());
            if (pacienteExistente.isPresent() && !pacienteExistente.get().getId().equals(paciente.getId())) {
                throw new IllegalArgumentException("Já existe um paciente cadastrado com esse CPF");
            }
            pacienteRepository.save(paciente);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public void excluir(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        pacienteRepository.delete(paciente.get());
        Util.escreveLog("Paciente ID " + id + " excluído com sucesso");
    }

    public Page<Paciente> listar(int pagina, int tamanho, String campoOrdenacao, boolean ascendente) {
        Util.escreveLog("Listando pacientes com paginação...");

        Sort.Direction direcaoOrdenacao = ascendente ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest paginaRequest = PageRequest.of(pagina, tamanho, direcaoOrdenacao, campoOrdenacao);

        return pacienteRepository.findAll(paginaRequest);
    }

    public Paciente buscarPorId(Long id) {
        Util.escreveLog("Paciente ID " + id + " Buscando sucesso");
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        Paciente paciente = pacienteOptional.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        return ResponseEntity.ok(paciente).getBody();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String mensagem) {
            super(mensagem);
        }
    }
}