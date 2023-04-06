package com.dh.sisoapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteRequest {
    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;

    private LocalDate dataNascimento;

    private EnderecoRequest endereco;
}
