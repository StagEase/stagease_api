package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.repository.SupervisorRepository;
import stag.ease.stagease.repository.UBSRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {
    @Autowired
    private SupervisorRepository repository;
    @Autowired
    private UBSRepository ubsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public SupervisorDTO getById(Long id) {
        Optional<SupervisorEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            SupervisorEntity entity = optional.get();
            return modelMapper.map(entity, SupervisorDTO.class);
        } else {
            throw new EntityNotFoundException("Área não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public SupervisorDTO findByNomeSupervisor(String nomeSupervisor) {
        return modelMapper.map(repository.findByNomeSupervisor(nomeSupervisor), SupervisorDTO.class);
    }
    @Transactional
    public SupervisorDTO findByMatricula(String matricula) {
        return modelMapper.map(repository.findByMatricula(matricula), SupervisorDTO.class);
    }
    @Transactional
    public List<SupervisorDTO> getAll() {
        List<SupervisorDTO> list = new ArrayList<>();
        for (SupervisorEntity entity : repository.findAll()) {
            SupervisorDTO map = modelMapper.map(entity, SupervisorDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public SupervisorDTO create(SupervisorDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, SupervisorEntity.class)), SupervisorDTO.class);
    }

    @Transactional
    public SupervisorDTO update(Long id, SupervisorDTO dto) {
        SupervisorEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área não encontrada com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), SupervisorDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
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
