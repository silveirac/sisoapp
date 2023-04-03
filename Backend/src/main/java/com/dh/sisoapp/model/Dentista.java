package com.dh.sisoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@ToString
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String sobrenome;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Column(unique = true)
    private String cro;
}
