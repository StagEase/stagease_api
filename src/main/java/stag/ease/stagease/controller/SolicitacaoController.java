package stag.ease.stagease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.dto.SolicitacaoDTO;
import stag.ease.stagease.repository.SolicitacaoRepository;
import stag.ease.stagease.service.SolicitacaoService;

import java.util.List;

@RestController
@RequestMapping(value = "/solicitacao")
public class SolicitacaoController {
    @Autowired
    private SolicitacaoService service;
    @Autowired
    private SolicitacaoRepository repository;


    @GetMapping("/list")
    public ResponseEntity<List<SolicitacaoDTO>> list() {
        return new ResponseEntity<>(service.getList(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SolicitacaoDTO> getById(@RequestParam("id") Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoDTO> create(@RequestBody @Validated SolicitacaoDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated SolicitacaoDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.valueOf("Flag desativada"));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
