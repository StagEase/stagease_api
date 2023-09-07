package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.DTO.UBSDTO;
import stag.ease.stagease.Entity.UBSEntity;
import stag.ease.stagease.Repository.UBSRepository;
import stag.ease.stagease.Service.UBSService;

import java.util.List;

@RestController
@RequestMapping(value = "/ubs")
public class UBSController {
    @Autowired
    private UBSService service;
    @Autowired
    private UBSRepository repository;

    @GetMapping("/nome")
    public ResponseEntity<UBSDTO> findByNomeUBS(@RequestParam("nome") String nome) {
        try {
            return new ResponseEntity<>(service.findByNomeUBS(nome), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UBSDTO>> findAll() {
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UBSDTO> create(@RequestBody @Validated UBSDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UBSDTO> update(@PathVariable("id") Long id, @RequestBody @Validated UBSDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            UBSEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
            repository.delete(entity);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
