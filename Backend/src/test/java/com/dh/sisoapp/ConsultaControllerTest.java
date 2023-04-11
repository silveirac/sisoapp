package com.dh.sisoapp;


import com.dh.sisoapp.controller.ConsultaController;
import com.dh.sisoapp.model.Consulta;
import com.dh.sisoapp.service.ConsultaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConsultaControllerTest {

    @Mock
    private ConsultaService consultaService;

    private ConsultaController consultaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        consultaController = new ConsultaController(consultaService);
    }

    @Test
    @DisplayName("Deve cadastrar uma consulta")
    void cadastrarConsulta_deveRetornarSucesso() {
        Consulta consulta = new Consulta();
        ResponseEntity<Object> response = consultaController.cadastrarConsulta(consulta);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(consultaService, times(1)).salvar(consulta);
    }

    @Test
    @DisplayName("Deve atualizar listar as consultas")
    void listarConsultas_deveRetornarSucesso() {
        List<Consulta> consultas = new ArrayList<>();
        when(consultaService.listar()).thenReturn(consultas);
        ResponseEntity<Object> response = consultaController.listarConsultas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consultas, response.getBody());
        verify(consultaService, times(1)).listar();
    }

    @Test
    @DisplayName("Deve atualizar uma consulta")
    void consultarPorId_deveRetornarSucesso() {
        Consulta consulta = new Consulta();
        when(consultaService.consultarPorId(1L)).thenReturn(consulta);
        ResponseEntity<Object> response = consultaController.consultarPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consulta, response.getBody());
        verify(consultaService, times(1)).consultarPorId(1L);
    }

    @Test
    @DisplayName("Deve excluir uma consulta")
    void excluirConsulta_deveRetornarSucesso() {
        Long idConsulta = 1L;
        ResponseEntity<Object> response = consultaController.excluirConsulta(idConsulta);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(consultaService, times(1)).excluir(idConsulta);
    }


}
