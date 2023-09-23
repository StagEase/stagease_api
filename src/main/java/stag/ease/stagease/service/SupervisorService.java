package stag.ease.stagease.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.repository.SupervisorRepository;
import stag.ease.stagease.repository.UBSRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorService {
    @Autowired
    private SupervisorRepository repository;
    @Autowired
    private UBSRepository ubsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public SupervisorDTO findByNomeSupervisor(String nomeSupervisor) {
        return modelMapper.map(repository.findByNomeSupervisor(nomeSupervisor), SupervisorDTO.class);
    }
    @Transactional
    public SupervisorDTO findByMatricula(String matricula) {
        return modelMapper.map(repository.findByMatricula(matricula), SupervisorDTO.class);
    }
    @Transactional
    public List<SupervisorDTO> getList() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, SupervisorDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public SupervisorDTO create(SupervisorDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id deve ser gerado pelo banco");
        }
        SupervisorEntity entity = repository.save(modelMapper.map(dto, SupervisorEntity.class));
        SupervisorDTO resultDTO = modelMapper.map(entity, SupervisorDTO.class);
        return resultDTO;
    }

    @Transactional
    public SupervisorDTO update(Long id, SupervisorDTO dto) {
        SupervisorEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, repository.save(entity));
        return modelMapper.map(entity, SupervisorDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        SupervisorEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        boolean ubsAtivo = entity.getUbsList().stream()
                .anyMatch(ubs -> ubsRepository.existsByIdAndAtivoIsTrue(ubs.getId()));
        if (ubsAtivo) {
            entity.setAtivo(false);
            repository.save(entity);
        } else {
            repository.delete(entity);
        }
    }
}
