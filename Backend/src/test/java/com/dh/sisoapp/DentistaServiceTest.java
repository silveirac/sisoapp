package com.dh.sisoapp;

import com.dh.sisoapp.model.Dentista;
import com.dh.sisoapp.repository.DentistaRepository;
import com.dh.sisoapp.service.DentistaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DentistaServiceTest {
    @Mock
    private DentistaRepository dentistaRepository;
    @InjectMocks
    private DentistaService dentistaService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testBuscarPorIdExistente() {
        Long id = 1L;
        Dentista dentista = new Dentista();
        dentista.setId(id);

        when(dentistaRepository.findById(id)).thenReturn(Optional.of(dentista));

        Dentista dentistaEncontrado = dentistaService.buscarPorId(id);

        assertEquals(dentista, dentistaEncontrado);
    }
    @Test
    public void testBuscarTodos() {
        List<Dentista> listaDentistas = new ArrayList<>();
        listaDentistas.add(new Dentista());
        listaDentistas.add(new Dentista());

        when(dentistaRepository.findAll()).thenReturn(listaDentistas);

        List<Dentista> listaEncontrada = dentistaService.listar();

        assertEquals(listaDentistas.size(), listaEncontrada.size());
    }
    @Test
    public void testSalvar() {
        Dentista dentista = new Dentista();
        dentista.setId(1L);

        when(dentistaRepository.save(dentista)).thenReturn(dentista);

        Dentista dentistaSalvo = dentistaService.salvar(dentista);

        assertEquals(dentista, dentistaSalvo);
    }
    @Test
    public void testExcluir() {
        Dentista dentista = new Dentista();
        dentista.setId(1L);
        when(dentistaRepository.existsById(1L)).thenReturn(true);
        dentistaRepository.deleteById(1L);
        assertTrue(dentistaRepository.existsById(1L));
    }
}