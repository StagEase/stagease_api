package stag.ease.stagease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.InstituicaoDeEnsinoDTO;


import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.service.InstituicaoDeEnsinoService;

import java.util.List;

@RestController
@RequestMapping(value = "/instituicao")
public class InstituicaoDeEnsinoController {
    @Autowired
    private InstituicaoDeEnsinoService service;

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
