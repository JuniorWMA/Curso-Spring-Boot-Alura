package com.alura.springalura.paciente;

import com.alura.springalura.endereco.DadosEndereco;


import jakarta.validation.constraints.NotNull;

public record PacienteAtualizacao(@NotNull Long idPaciente, String nome, String email, String telefone, boolean ativo,DadosEndereco endereco) {
    
}
