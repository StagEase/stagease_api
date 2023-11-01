package stag.ease.stagease.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.entity.InstituicaoDeEnsinoEntity;
import stag.ease.stagease.repository.InstituicaoDeEnsinoRepository;
import stag.ease.stagease.service.InstituicaoDeEnsinoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/instituicao")
public class InstituicaoDeEnsinoController {
    @Autowired
    private InstituicaoDeEnsinoService service;
    @Autowired
    private InstituicaoDeEnsinoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), InstituicaoDeEnsinoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> getByNome(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(modelMapper.map(service.findByNome(nome), InstituicaoDeEnsinoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<InstituicaoDeEnsinoDTO>> getAll() {
        List<InstituicaoDeEnsinoDTO> list = new ArrayList<>();
        for (InstituicaoDeEnsinoEntity entity : service.getAll()) {
            InstituicaoDeEnsinoDTO map = modelMapper.map(entity, InstituicaoDeEnsinoDTO.class);
            list.add(map);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstituicaoDeEnsinoDTO> create(@RequestBody @Validated InstituicaoDeEnsinoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, InstituicaoDeEnsinoEntity.class)), InstituicaoDeEnsinoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated InstituicaoDeEnsinoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, InstituicaoDeEnsinoEntity.class)), InstituicaoDeEnsinoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
