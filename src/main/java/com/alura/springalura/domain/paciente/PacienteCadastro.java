package com.alura.springalura.domain.paciente;

import com.alura.springalura.domain.endereco.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteCadastro (
    @NotBlank
    String nome,

    @NotBlank
    String email,

    @NotBlank
    String telefone,
    
    @NotNull
    @Valid
    Endereco endereco
    
    
    ){

}
