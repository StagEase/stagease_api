package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<AreaDTO> buscar(@PathVariable("nome") String nomeArea) {
        AreaDTO area = service.findByNomeArea(nomeArea);
        return ResponseEntity.ok(area);
    }

    @PostMapping
    public ResponseEntity<AreaDTO> create(@RequestBody final AreaDTO dto) {
        try {
            return ResponseEntity.ok(this.service.create(dto));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDTO> update(@PathVariable("id") final Long id, @RequestBody final AreaDTO dto) {
        try {
            return ResponseEntity.ok(service.update(id, dto));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") final Long id) {
       final AreaEntity local = this.repository.findById(id).orElse(null);
       try {
           this.repository.delete(local);
           return ResponseEntity.ok("Local deletado com sucesso");
       } catch (IllegalArgumentException e) {
           return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
       }
    }
}