package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    @GetMapping("/list")
    public ResponseEntity<List<UBSEntity>> list() {
        List<UBSEntity> lista = repository.findAll();

        return new ResponseEntity<>(lista, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<UBSEntity> searchByNomeUBS(@RequestParam("nome") final String nome) {
        UBSEntity entity = repository.findByNomeUBS(nome);

        return new ResponseEntity<>(entity, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<UBSEntity> create(@RequestBody @Validated final UBSDTO dto) {
        UBSEntity entity = new UBSEntity();
        try {
            entity = service.create(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UBSEntity> update(@PathVariable("id") final Long id, @RequestBody @Validated final UBSDTO dto) {
        UBSEntity entity = new UBSEntity();
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
            UBSEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
            repository.delete(entity);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
