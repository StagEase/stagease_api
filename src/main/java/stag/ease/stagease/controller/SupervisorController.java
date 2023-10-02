package stag.ease.stagease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.repository.SupervisorRepository;
import stag.ease.stagease.service.SupervisorService;

import java.util.List;

@RestController
@RequestMapping(value = "/supervisor")
public class SupervisorController {
    @Autowired
    private SupervisorService service;
    @Autowired
    private SupervisorRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<SupervisorDTO> getByNomeSupervisor(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(service.findByNomeSupervisor(nome), HttpStatus.OK);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<SupervisorDTO> getByNomeMatricula(@PathVariable("matricula") String matricula) {
        return new ResponseEntity<>(service.findByMatricula(matricula), HttpStatus.OK);
    }

    // pesquisar pela area de atuacao faltaria

    @GetMapping("/list")
    public ResponseEntity<List<SupervisorDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SupervisorDTO> create(@RequestBody @Validated SupervisorDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDTO> update(@PathVariable("id") Long id, @RequestBody @Validated SupervisorDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
