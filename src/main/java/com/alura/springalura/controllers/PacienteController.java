package com.alura.springalura.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.springalura.domain.paciente.Paciente;
import com.alura.springalura.domain.paciente.PacienteAtualizacao;
import com.alura.springalura.domain.paciente.PacienteCadastro;
import com.alura.springalura.domain.paciente.PacienteDetalhamento;
import com.alura.springalura.domain.paciente.PacienteRepositorio;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/paciente")
public class PacienteController {
    @Autowired
    private PacienteRepositorio repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PacienteCadastro pacienteCadastro, UriComponentsBuilder uri) {
        var paciente = new Paciente(pacienteCadastro);
        repositorio.save(paciente);

        var uriPaciente = uri.path("paciente/{idPaciente}").buildAndExpand(paciente.getIdPaciente()).toUri();

        return ResponseEntity.created(uriPaciente).body(new PacienteDetalhamento(paciente));
    }

    @GetMapping
    public ResponseEntity<List<PacienteDetalhamento>> listar() {
        var lista = repositorio.findAll().stream().map(PacienteDetalhamento::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/lista-pag") 
    public ResponseEntity<Page<PacienteDetalhamento>> listarPaginada(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repositorio.findAllByAtivoTrue(paginacao).map(PacienteDetalhamento::new);
        return ResponseEntity.ok(page);
    }
        
    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid PacienteAtualizacao atualizacao) {
        var pacientePeloId = repositorio.getReferenceById(atualizacao.idPaciente());
        pacientePeloId.atualizaPaciente(atualizacao);
        return ResponseEntity.ok(new PacienteDetalhamento(pacientePeloId));

    }
     @DeleteMapping("/{idPaciente}")
     @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long idPaciente) {
        var paciente = repositorio.getReferenceById(idPaciente);
        paciente.excluir();
        return ResponseEntity.noContent().build();
        
    }
    @PutMapping("/inativar{idPaciente}")
    @Transactional
    public ResponseEntity<?> inativar(@PathVariable @RequestBody @Valid Long idPaciente) {
        var paciente1 = repositorio.getReferenceById(idPaciente);
        paciente1.excluir();

        return ResponseEntity.ok(new PacienteDetalhamento(paciente1));
    }

    
}
