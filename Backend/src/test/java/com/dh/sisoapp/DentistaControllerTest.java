package com.dh.sisoapp;

import com.dh.sisoapp.controller.DentistaController;
import com.dh.sisoapp.controller.dto.DentistaRequest;
import com.dh.sisoapp.controller.dto.DentistaResponse;
import com.dh.sisoapp.service.DentistaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DentistaControllerTest {

    @Mock
    private DentistaService dentistaService;

    @InjectMocks
    private DentistaController dentistaController;

    private DentistaRequest dentistaRequest;

    private DentistaResponse dentistaResponse;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dentistaController = new DentistaController(dentistaService);

        dentistaRequest = new DentistaRequest("Pedro", "Cardoso", "fulano@test.com", "111.222.333-44", "12345");
        dentistaResponse = new DentistaResponse("Antonio", "Nunes", "fulano@test.com", "111.222.333-44", "12345");
    }

    @Test
    @DisplayName("Deve retornar um dentista por ID")
    public void buscarPorIDTest() {
        when(dentistaService.buscarPorId(id)).thenReturn(dentistaResponse);

        ResponseEntity<Object> resposta = dentistaController.buscarPorID(id);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve listar todos os dentistas")
    public void listarDentistasTest() {
        List<DentistaResponse> dentistas = new ArrayList<>();
        dentistas.add(dentistaResponse);
        when(dentistaService.listar()).thenReturn(dentistas);

        ResponseEntity<Object> resposta = dentistaController.listarDentistas();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve cadastrar um novo dentista")
    public void cadastrarDentistaTest() {
        Mockito.when(dentistaService.salvar(Mockito.any(DentistaRequest.class)))
                .thenReturn(new DentistaResponse());

        ResponseEntity<Object> resposta = dentistaController.cadastrarDentista(dentistaRequest);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve atualizar um dentista")
    public void atualizarDentistaTest() {
        doNothing().when(dentistaService).atualizar(anyLong(), any(DentistaRequest.class));

        ResponseEntity<Object> resposta = dentistaController.atualizarDentista(id, dentistaRequest);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve excluir um dentista")
    public void excluirDentistaTest() {
        doNothing().when(dentistaService).excluir(anyLong());

        ResponseEntity<Object> resposta = dentistaController.excluirDentista(id);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
    }
}