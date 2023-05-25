package com.alura.springalura.domain.paciente;

import com.alura.springalura.domain.endereco.Endereco;

public record PacienteDetalhamento (Long idPaciente, String nome, String email, String telefone, Boolean ativo, Endereco endereco) {

    public PacienteDetalhamento(Paciente paciente){
        this(paciente.getIdPaciente(),
        paciente.getNome(),
        paciente.getEmail(),
        paciente.getTelefone(),
        paciente.isAtivo(),
        paciente.getEndereco()
        );
    }
}
