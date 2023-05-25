package com.alura.springalura.domain.medico;

import com.alura.springalura.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id, 
    String nome, 
    String telefone,
    DadosEndereco endereco) {

}
