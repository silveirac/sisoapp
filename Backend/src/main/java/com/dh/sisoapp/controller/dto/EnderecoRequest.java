package com.dh.sisoapp.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoRequest {
    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String uf;

    private String cep;
}
