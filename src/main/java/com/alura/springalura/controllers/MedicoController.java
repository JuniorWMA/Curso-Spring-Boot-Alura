package com.alura.springalura.controllers;



import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

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

import com.alura.springalura.domain.medico.DadosAtualizacaoMedico;
import com.alura.springalura.domain.medico.DadosCadastroMedico;
import com.alura.springalura.domain.medico.DadosDetalhamentoMedico;
import com.alura.springalura.domain.medico.DadosListagemMedico;
import com.alura.springalura.domain.medico.Medico;
import com.alura.springalura.domain.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dados);
        medicoRepository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var lista = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/listar-todos")
    public ResponseEntity<Stream<DadosListagemMedico>> listar() {
        var lista = medicoRepository.findAll().stream().map(DadosListagemMedico::new);
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
       var medico = medicoRepository.getReferenceById(dados.id());
       medico.atualizarInformacoes(dados);

       return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
       medico.excluir();

       return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    
}
