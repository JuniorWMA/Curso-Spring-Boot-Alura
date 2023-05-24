package com.alura.springalura.paciente;

import com.alura.springalura.endereco.Endereco;

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
