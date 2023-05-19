package com.alura.springalura.medico;

import com.alura.springalura.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id, 
    String nome, 
    String telefone,
    DadosEndereco endereco) {

}
