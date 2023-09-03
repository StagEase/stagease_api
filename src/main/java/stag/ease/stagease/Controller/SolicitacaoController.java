package stag.ease.stagease.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final SolicitacaoService service;
    private final SolicitacaoRepository repository;

    @Autowired
    public SolicitacaoController(SolicitacaoService service, SolicitacaoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SolicitacaoEntity>> list() {
        List<SolicitacaoEntity> lista = repository.findAll();
        return new ResponseEntity<>(lista, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoEntity> create(@RequestBody @Validated final SolicitacaoDTO dto) {
        SolicitacaoEntity entity = new SolicitacaoEntity();
        try {
            entity = service.create(dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoEntity> update(@PathVariable("id") final Long id, @RequestBody @Validated final SolicitacaoDTO dto) {
        SolicitacaoEntity entity = new SolicitacaoEntity();
        try {
            entity = service.update(id, dto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id){
        try {
            SolicitacaoEntity entity = repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel encontrar o id informado"));
            entity.setAtivo(false);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.valueOf("Flag desativada"));
    }
}
