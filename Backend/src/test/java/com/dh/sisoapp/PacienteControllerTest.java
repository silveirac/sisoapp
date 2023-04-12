package com.dh.sisoapp;

import com.dh.sisoapp.controller.PacienteController;
import com.dh.sisoapp.controller.dto.EnderecoRequest;
import com.dh.sisoapp.controller.dto.PacienteRequest;
import com.dh.sisoapp.controller.dto.PacienteResponse;
import com.dh.sisoapp.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class PacienteControllerTest {

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private PacienteController pacienteController;

    private PacienteRequest pacienteRequest;

    private PacienteResponse pacienteResponse;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pacienteController = new PacienteController(pacienteService);

        EnderecoRequest endereco = new EnderecoRequest("Rua Da Flores", "123", "Jardim", "Das Pedras", "SC", "88501245");
        pacienteRequest = new PacienteRequest("Antonio", "Nunes", "fulano@test.com", "123.13.123-52", LocalDate.of(2020,1,12),endereco);

    }

    @Test
    @DisplayName("Deve retornar um paciente por ID")
    public void buscarPorIDTest() {
        when(pacienteService.buscarPorId(id)).thenReturn(pacienteResponse);
        ResponseEntity<Object> resposta = pacienteController.buscarPorID(id);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Deve listar todos os paciente")
    public void listarDentistasTest() {
        List<PacienteResponse> pacientes = new ArrayList<>();
        pacientes.add(pacienteResponse);
        when(pacienteService.listar()).thenReturn(pacientes);

        ResponseEntity<Object> resposta = pacienteController.listarPacientes();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve cadastrar um novo paciente")
    public void cadastrarDentistaTest() {
        Mockito.when(pacienteService.salvar(Mockito.any(PacienteRequest.class)))
                .thenReturn(new PacienteResponse());

        ResponseEntity<Object> resposta = pacienteController.cadastrarPaciente(pacienteRequest);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve atualizar um paciente")
    public void atualizarDentistaTest() {
        doNothing().when(pacienteService).atualizar(anyLong(), any(PacienteRequest.class));
        ResponseEntity<Object> resposta = pacienteController.atualizarPaciente(id, pacienteRequest);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve excluir um paciente")
    public void excluirDentistaTest() {
        doNothing().when(pacienteService).excluir(anyLong());

        ResponseEntity<Object> resposta = pacienteController.excluirPaciente(id);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
    }
}