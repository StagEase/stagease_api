package stag.ease.stagease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.dto.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.entity.InstituicaoDeEnsinoEntity;
import stag.ease.stagease.repository.InstituicaoDeEnsinoRepository;
import stag.ease.stagease.service.InstituicaoDeEnsinoService;

import java.util.List;

@RestController
@RequestMapping(value = "/instituicao")
public class InstituicaoDeEnsinoController {
    @Autowired
    private InstituicaoDeEnsinoService service;
    @Autowired
    private InstituicaoDeEnsinoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> getByNome(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(service.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<InstituicaoDeEnsinoDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstituicaoDeEnsinoDTO> create(@RequestBody @Validated InstituicaoDeEnsinoDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated InstituicaoDeEnsinoDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        InstituicaoDeEnsinoEntity database = repository.findById(id).orElse(null);

        if (database == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado");
        }

        try {
            repository.delete(database);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (RuntimeException e) {
            if (database.isAtivo()) {
                database.setAtivo(false);
                repository.save(database);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no delete, flag desativada!");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no delete, a flag já está desativada");
        }
    }
}
