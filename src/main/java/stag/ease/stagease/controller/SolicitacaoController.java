package stag.ease.stagease.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.dto.SolicitacaoDTO;
import stag.ease.stagease.entity.*;
import stag.ease.stagease.repository.AreaRepository;
import stag.ease.stagease.repository.EquipamentoRepository;
import stag.ease.stagease.repository.InstituicaoDeEnsinoRepository;
import stag.ease.stagease.repository.SupervisorRepository;
import stag.ease.stagease.service.SolicitacaoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/solicitacao")
public class SolicitacaoController {
    @Autowired
    private SolicitacaoService service;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    @Autowired
    private InstituicaoDeEnsinoRepository instituicaoDeEnsinoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(modelMapper.map(service.getById(id), SolicitacaoDTO.class), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<SolicitacaoDTO>> list() {
        List<SolicitacaoDTO> list = new ArrayList<>();
        for (SolicitacaoEntity entity : service.getAll()) {
            SolicitacaoDTO map = modelMapper.map(entity, SolicitacaoDTO.class);
            list.add(map);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@PostMapping("/")
    public ResponseEntity<SolicitacaoDTO> create(@RequestBody @Validated SolicitacaoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.create(modelMapper.map(dto, SolicitacaoEntity.class)), SolicitacaoDTO.class), HttpStatus.CREATED);
    }*/

    @PostMapping("/")
    public ResponseEntity<SolicitacaoDTO> create(@RequestBody @Validated SolicitacaoDTO dto) {
        EquipamentoEntity equipamentoEntity = equipamentoRepository.findById(dto.getEquipamento().getId())
                .orElseThrow(() -> new EntityNotFoundException("Equipamento não encontrado com o ID: " + dto.getEquipamento().getId()));
        AreaEntity areaEntity = areaRepository.findById(dto.getArea().getId())
                .orElseThrow(() -> new EntityNotFoundException("Área não encontrada com o ID: " + dto.getArea().getId()));
        SupervisorEntity supervisorEntity = supervisorRepository.findById(dto.getSupervisor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Supervisor não encontrado com o ID: " + dto.getSupervisor().getId()));
        InstituicaoDeEnsinoEntity instituicaoDeEnsinoEntity = instituicaoDeEnsinoRepository.findById(dto.getInstituicaoDeEnsino().getId())
                .orElseThrow(() -> new EntityNotFoundException("Instituição de ensino não encontrada com o ID: " + dto.getInstituicaoDeEnsino().getId()));

        SolicitacaoEntity solicitacaoEntity = modelMapper.map(dto, SolicitacaoEntity.class);

        solicitacaoEntity.setEquipamento(equipamentoEntity);
        solicitacaoEntity.setArea(areaEntity);
        solicitacaoEntity.setSupervisor(supervisorEntity);
        solicitacaoEntity.setInstituicaoDeEnsino(instituicaoDeEnsinoEntity);

        SolicitacaoEntity createdSolicitacaoEntity = service.create(solicitacaoEntity);

        SolicitacaoDTO responseDTO = modelMapper.map(createdSolicitacaoEntity, SolicitacaoDTO.class);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> update(@PathVariable("id") Long id, @RequestBody @Validated SolicitacaoDTO dto) {
        return new ResponseEntity<>(modelMapper.map(service.update(id, modelMapper.map(dto, SolicitacaoEntity.class)), SolicitacaoDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        }
    }
}
