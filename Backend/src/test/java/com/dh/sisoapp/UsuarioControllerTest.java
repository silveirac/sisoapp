package com.dh.sisoapp;


import com.dh.sisoapp.controller.UsuarioController;
import com.dh.sisoapp.controller.dto.DentistaResponse;
import com.dh.sisoapp.controller.dto.UsuarioRequest;
import com.dh.sisoapp.controller.dto.UsuarioResponse;
import com.dh.sisoapp.service.UsuarioService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private UsuarioController usuarioController;
    private UsuarioRequest usuarioRequest;
    private UsuarioResponse dentistaResponse;
    private final Long id = 1L;
    private com.dh.sisoapp.controller.dto.UsuarioResponse UsuarioResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioController = new UsuarioController(usuarioService);

        usuarioRequest = new UsuarioRequest("Pedro", "fulano@test.com", "123456", "Admin");
    }

    @Test
    @DisplayName("Deve retornar um usuario por ID")
    public void buscarPorIDTest() {

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setEmail("fulano@teste.com");

        when(usuarioService.buscarPorId(id)).thenReturn(usuarioResponse);

        ResponseEntity<Object> resposta = usuarioController.buscarPorID(id);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertTrue(resposta.getBody() instanceof UsuarioResponse);

        UsuarioResponse usuarioEncontrado = (UsuarioResponse) resposta.getBody();
        assertEquals("fulano@teste.com", usuarioEncontrado.getEmail());
    }

    @Test
    @DisplayName("Deve listar todos os usuarios")
    public void listarDentistasTest() {
        List<UsuarioResponse> usuarios = new ArrayList<>();
        usuarios.add(UsuarioResponse);
        when(usuarioService.listar()).thenReturn(usuarios);

        ResponseEntity<Object> resposta = usuarioController.listarUsuarios();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário")
    public void cadastrarUsuarioTest() {
        Mockito.when(usuarioService.salvar(Mockito.any(UsuarioRequest.class)))
                .thenReturn(new UsuarioResponse());

        ResponseEntity<Object> resposta = usuarioController.cadastrarUsuario(usuarioRequest);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    public void atualizarUsuarioTest() {
        doNothing().when(usuarioService).atualizar(anyLong(), any(UsuarioRequest.class));

        ResponseEntity<Object> resposta = usuarioController.atualizarUsuario(id, usuarioRequest);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve excluir um usuário")
    public void excluirUsuarioTest() {
        doNothing().when(usuarioService).excluir(anyLong());

        ResponseEntity<Object> resposta = usuarioController.excluirUsuario(id);

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
    }
}