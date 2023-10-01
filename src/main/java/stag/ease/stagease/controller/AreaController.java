package stag.ease.stagease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.repository.AreaRepository;
import stag.ease.stagease.service.AreaService;

import java.util.List;

@RestController
@RequestMapping(value = "/area")
public class AreaController {
    @Autowired
    private AreaService service;
    @Autowired
    private AreaRepository repository;

    @GetMapping
    public ResponseEntity<AreaDTO> getById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<AreaDTO> getByNomeArea(@RequestParam("nome") String nome) {
        return new ResponseEntity<>(service.getByNomeArea(nome), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AreaDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AreaDTO> create(@RequestBody @Validated AreaDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDTO> update(@PathVariable("id") Long id, @RequestBody @Validated AreaDTO dto) {
        return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}