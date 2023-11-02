package stag.ease.stagease.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.service.SupervisorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/supervisor")
public class SupervisorController {
    @Autowired
    private SupervisorService service;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), SupervisorDTO.class), HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<SupervisorDTO> getByNomeSupervisor(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(modelMapper.map(service.findByNomeSupervisor(nome), SupervisorDTO.class), HttpStatus.OK);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<SupervisorDTO> getByNomeMatricula(@PathVariable("matricula") String matricula) {
        return new ResponseEntity<>(modelMapper.map(service.findByMatricula(matricula), SupervisorDTO.class), HttpStatus.OK);
    }

    // pesquisar pela area de atuacao faltaria

    @GetMapping("/list")
    public ResponseEntity<List<SupervisorDTO>> getAll() {
        List<SupervisorDTO> list = new ArrayList<>();
        for (SupervisorEntity entity : service.getAll()) {
            SupervisorDTO map = modelMapper.map(entity, SupervisorDTO.class);
            list.add(map);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SupervisorDTO> create(@RequestBody @Validated SupervisorDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, SupervisorEntity.class)), SupervisorDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDTO> update(@PathVariable("id") Long id, @RequestBody @Validated SupervisorDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, SupervisorEntity.class)), SupervisorDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
