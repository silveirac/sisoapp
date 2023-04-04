package com.dh.sisoapp;

import com.dh.sisoapp.model.Endereco;
import com.dh.sisoapp.repository.IEnderecoRepository;
import com.dh.sisoapp.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EnderecoServiceTest {

    @Mock
    private IEnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvar() {
        Endereco endereco = new Endereco();
        endereco.setId(2L);
        endereco.setRua("Test");
        endereco.setBairro("Centro");
        endereco.setCidade("Florianópolis");
        endereco.setUf("SC");
        endereco.setNumero("1234");
        endereco.setCep("88000-000");

        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        enderecoService.salvar(endereco);

        assertNotNull(endereco.getId());
    }

    @Test
    void listar() {
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco());
        enderecos.add(new Endereco());

        when(enderecoRepository.findAll()).thenReturn(enderecos);

        List<Endereco> result = enderecoService.listar();

        assertEquals(2, result.size());
    }

    @Test
    void buscarPorId() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        Endereco result = enderecoService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void atualizar() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setRua("Rua Teste, 123");
        endereco.setCidade("Florianópolis");
        endereco.setUf("SC");
        endereco.setCep("88000-000");

        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(enderecoRepository.existsById(1L)).thenReturn(true);

        enderecoService.atualizar(endereco);

        assertEquals("88000-000", endereco.getCep());
    }

    @Test
    void excluir() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);

        when(enderecoRepository.existsById(1L)).thenReturn(true);

        enderecoService.excluir(1L);

        verify(enderecoRepository).deleteById(1L);
        assertTrue(enderecoRepository.existsById(1L));
    }

}
