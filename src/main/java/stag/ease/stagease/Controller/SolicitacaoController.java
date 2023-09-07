package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.SolicitacaoDTO;
import stag.ease.stagease.Entity.SolicitacaoEntity;
import stag.ease.stagease.Repository.SolicitacaoRepository;
import stag.ease.stagease.Service.SolicitacaoService;

import java.util.List;

@RestController
@RequestMapping(value = "/solicitacao")
public class SolicitacaoController {
    @Autowired
    private SolicitacaoService service;
    @Autowired
    private SolicitacaoRepository repository;


    @GetMapping("/list")
    public List<SolicitacaoDTO> list() {
        try {
            return service.getList();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SolicitacaoDTO> create(@RequestBody @Validated final SolicitacaoDTO dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> update(@PathVariable("id") final Long id, @RequestBody @Validated final SolicitacaoDTO dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.valueOf("Flag desativada"));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
