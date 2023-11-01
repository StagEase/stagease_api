package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.repository.SupervisorRepository;
import stag.ease.stagease.repository.UBSRepository;

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
    public SupervisorEntity getById(Long id) {
        Optional<SupervisorEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Supervisor não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public SupervisorEntity findByNomeSupervisor(String nomeSupervisor) {
        return repository.findByNomeSupervisor(nomeSupervisor);
    }

    @Transactional
    public SupervisorEntity findByMatricula(String matricula) {
        return repository.findByMatricula(matricula);
    }
    @Transactional
    public List<SupervisorEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public SupervisorEntity create(SupervisorEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public SupervisorEntity update(Long id, SupervisorEntity entity) {
        SupervisorEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor não encontrado com o ID: " + id));
        modelMapper.map(entity, existingEntity);
        return repository.save(existingEntity);
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
