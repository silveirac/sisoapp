package com.dh.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String rg;
    private Date dataAlta;
    private Endereco endereco;

    public Paciente(String nome, String sobrenome, String rg, Date dataAlta, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.dataAlta = dataAlta;
        this.endereco = endereco;
    }
}
