package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.SupervisorDTO;
import stag.ease.stagease.Entity.SupervisorEntity;
import stag.ease.stagease.Repository.SupervisorRepository;
import stag.ease.stagease.Service.SupervisorService;

import java.util.List;

@RestController
@RequestMapping(value = "/supervisor")
public class SupervisorController {
    @Autowired
    private SupervisorService service;
    @Autowired
    private SupervisorRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<SupervisorEntity>> list() {
        List<SupervisorEntity> lista = repository.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SupervisorDTO> create(@RequestBody @Validated SupervisorDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDTO> update(@PathVariable("id") Long id, @RequestBody @Validated SupervisorDTO dto) {
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
