package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.repository.AreaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class    AreaService {
    @Autowired
    private AreaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AreaEntity getById(Long id) {
        Optional<AreaEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Área não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public AreaEntity getByNomeArea(String nome) {
        return repository.findByNomeArea(nome);
    }

    @Transactional
    public List<AreaEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public AreaEntity create(AreaEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public AreaEntity update(Long id, AreaEntity entity) {
        AreaEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área não encontrada com o ID: " + id));

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
