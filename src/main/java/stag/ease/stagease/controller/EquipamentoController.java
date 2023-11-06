package stag.ease.stagease.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.EquipamentoDTO;
import stag.ease.stagease.entity.EquipamentoEntity;
import stag.ease.stagease.service.EquipamentoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/equipamento")
public class EquipamentoController {
    @Autowired
    private EquipamentoService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), EquipamentoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<EquipamentoDTO> getByNomeEquipamento(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(modelMapper.map(service.getByNomeEquipamento(nome), EquipamentoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EquipamentoDTO>> getAll() {
        List<EquipamentoDTO> list = new ArrayList<>();
        for (EquipamentoEntity entity : service.getAll()) {
            EquipamentoDTO map = modelMapper.map(entity, EquipamentoDTO.class);
            list.add(map);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EquipamentoDTO> create(@RequestBody @Validated EquipamentoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, EquipamentoEntity.class)), EquipamentoDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated EquipamentoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, EquipamentoEntity.class)), EquipamentoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
