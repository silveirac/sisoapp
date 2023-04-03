package com.dh.sisoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String rua;

    @NotNull
    private String numero;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
