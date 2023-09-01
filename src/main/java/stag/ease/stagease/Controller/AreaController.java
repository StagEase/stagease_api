package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Repository.AreaRepository;
import stag.ease.stagease.Service.AreaService;

import java.util.List;

@RestController
@RequestMapping(value = "/area")
public class AreaController {
    @Autowired
    private AreaService service;
    @Autowired
    private AreaRepository repository;

    @GetMapping("/list")
    public ResponseEntity<List<AreaEntity>> list() {
        List<AreaEntity> lista = repository.findAll();

        return new ResponseEntity<>(lista, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<AreaEntity> searchByNomeArea(@RequestParam("nome") final String nomeArea) {
        AreaEntity entity = repository.findByNomeArea(nomeArea);

        return new ResponseEntity<>(entity, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<AreaEntity> create(@RequestBody @Validated final AreaDTO dto) {
        AreaEntity entity = new AreaEntity();
        try {
            entity = service.create(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaEntity> update(@PathVariable("id") final Long id, @RequestBody @Validated final AreaDTO dto) {
        AreaEntity entity = new AreaEntity();
        try {
            entity = service.update(id, dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            AreaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
            repository.delete(entity);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}