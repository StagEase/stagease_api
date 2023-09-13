package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.Repository.InstituicaoDeEnsinoRepository;
import stag.ease.stagease.Service.InstituicaoDeEnsinoService;

import java.util.List;

@RestController
@RequestMapping(value = "/instituicao_de_ensino")
public class InstituicaoDeEnsinoController {
    @Autowired
    private InstituicaoDeEnsinoService service;
    @Autowired
    private InstituicaoDeEnsinoRepository repository;

    @GetMapping("/list")
    public List<InstituicaoDeEnsinoDTO> list() {
        try {
            return service.getList();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<InstituicaoDeEnsinoDTO> create(@RequestBody @Validated InstituicaoDeEnsinoDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoDeEnsinoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated InstituicaoDeEnsinoDTO dto) {
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
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.valueOf("Excluido com sucesso"));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
