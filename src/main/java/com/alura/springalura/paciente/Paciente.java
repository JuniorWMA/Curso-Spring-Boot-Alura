package com.alura.springalura.paciente;

import com.alura.springalura.endereco.Endereco;
import com.alura.springalura.medico.DadosCadastroMedico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idPaciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long idPaciente;
    
    private String nome;

    
    private String email;
   
    private String telefone;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Paciente(PacienteCadastro cadastro) {
        this.nome = cadastro.nome();
        this.email = cadastro.email();
        this.telefone = cadastro.telefone();
        this.endereco = cadastro.endereco();
        this.ativo = true;
    }

    public void atualizaPaciente(PacienteAtualizacao paciente) {
        if (paciente.nome() != null) {
            this.nome = paciente.nome();
        }
        if (paciente.email()!=null) {
            this.email = paciente.email();
        }
        if (paciente.telefone() != null) {
            this.telefone = paciente.telefone();
        }

        if (paciente.endereco()!= null) {
            this.endereco.atualizarInformacoes(paciente.endereco());
        }
        
        
    }

    public void excluir() {
        this.ativo = false;
    }


    
    
}
