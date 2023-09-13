package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.SolicitacaoDTO;
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

    @GetMapping("/nome")
    public ResponseEntity<SupervisorDTO> findByNomeSupervisor(@RequestParam("nome") String nome) {
        try {
            return new ResponseEntity<>(service.findByNomeSupervisor(nome), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/matricula")
    public ResponseEntity<SupervisorDTO> findByMatricula(@RequestParam("matricula") String matricula) {
        try {
            return new ResponseEntity<>(service.findByMatricula(matricula), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // pesquisar pela area de atuacao faltaria

    @GetMapping("/list")
    public List<SupervisorDTO> list() {
        try {
            return service.getList();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
