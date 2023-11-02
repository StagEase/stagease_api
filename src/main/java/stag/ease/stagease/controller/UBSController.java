package stag.ease.stagease.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.entity.UBSEntity;
import stag.ease.stagease.service.UBSService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ubs")
public class UBSController {
    @Autowired
    private UBSService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UBSDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), UBSDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<UBSDTO> getByNomeUBS(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(modelMapper.map(service.getByNomeUBS(nome), UBSDTO.class), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UBSDTO>> getAll() {
        List<UBSDTO> list = new ArrayList<>();
        for (UBSEntity entity : service.getAll()) {
            UBSDTO map = modelMapper.map(entity, UBSDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UBSDTO> create(@RequestBody @Validated UBSDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, UBSEntity.class)), UBSDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UBSDTO> update(@PathVariable("id") Long id, @RequestBody @Validated UBSDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, UBSEntity.class)), UBSDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
