package com.dh.sisoapp;

import com.dh.sisoapp.model.Paciente;
import com.dh.sisoapp.repository.PacienteRepository;
import com.dh.sisoapp.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class PacienteServiceTest {

    @InjectMocks
    private PacienteService pacienteService;

    @Mock
    private PacienteRepository pacienteRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPacientePorId() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("João");
        paciente.setEmail("joao@gmail.com");
        paciente.setCpf("123.456.789-10");
        paciente.setDataNascimento(LocalDate.of(1990, 10, 10));

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente pacienteEncontrado = pacienteService.buscarPorId(1L);

        assertEquals(paciente, pacienteEncontrado);
    }

    @Test
    public void testBuscarTodosOsPacientes() {
        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);
        paciente1.setNome("João");
        paciente1.setEmail("joao@gmail.com");
        paciente1.setCpf("123.456.789-10");
        paciente1.setDataNascimento(LocalDate.of(1990, 10, 10));

        Paciente paciente2 = new Paciente();
        paciente2.setId(2L);
        paciente2.setNome("Maria");
        paciente2.setEmail("maria@gmail.com");
        paciente2.setCpf("987.654.321-10");
        paciente2.setDataNascimento(LocalDate.of(1995, 5, 5));

        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);

        when(pacienteRepository.findAll()).thenReturn(pacientes);

        List<Paciente> pacientesEncontrados = (List<Paciente>) pacienteService.listar(0, 10, "nome", true);

        assertEquals(pacientes, pacientesEncontrados);
    }

    @Test
    public void testSalvarPaciente() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("João");
        paciente.setEmail("joao@gmail.com");
        paciente.setCpf("123.456.789-10");
        paciente.setDataNascimento(LocalDate.of(1990, 10, 10));

        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente pacienteSalvo = pacienteService.salvar(paciente);

        assertEquals(paciente, pacienteSalvo);
    }

    @Test
    public void testExcluirPaciente() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("João");
        paciente.setSobrenome("Nunes");
        paciente.setEmail("joao@gmail.com");
        paciente.setCpf("123.456.789-10");
        paciente.setDataNascimento(LocalDate.of(1990, 10, 10));

        when(pacienteRepository.existsById(1L)).thenReturn(true);

        pacienteRepository.deleteById(1L);

        assertTrue(pacienteRepository.existsById(1L));
    }
}
