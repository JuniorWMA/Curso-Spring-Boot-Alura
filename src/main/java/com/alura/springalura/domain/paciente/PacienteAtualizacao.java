package com.alura.springalura.domain.paciente;

import com.alura.springalura.domain.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record PacienteAtualizacao(@NotNull Long idPaciente, String nome, String email, String telefone, boolean ativo,DadosEndereco endereco) {
    
}
