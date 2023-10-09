package stag.ease.stagease.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.service.AreaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/area")
public class AreaController {
    @Autowired
    private AreaService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AreaDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), AreaDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<AreaDTO> getByNomeArea(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(modelMapper.map(service.getByNomeArea(nome), AreaDTO.class), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AreaDTO>> getAll() {
        List<AreaDTO> list = new ArrayList<>();
        for (AreaEntity entity : service.getAll()) {
            AreaDTO map = modelMapper.map(entity, AreaDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AreaDTO> create(@RequestBody @Validated AreaDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, AreaEntity.class)), AreaDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDTO> update(@PathVariable("id") Long id, @RequestBody @Validated AreaDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, AreaEntity.class)), AreaDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}