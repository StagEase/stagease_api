package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.entity.UBSEntity;
import stag.ease.stagease.repository.UBSRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UBSService {
    @Autowired
    private UBSRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UBSEntity getById(Long id) {
        Optional<UBSEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("UBS não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public UBSEntity getByNomeUBS(String nome) {
        return repository.findByNomeUBS(nome);
    }

    @Transactional
    public List<UBSEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public UBSEntity create(UBSEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public UBSEntity update(Long id, UBSEntity entity) {
        UBSEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UBS não encontrada com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
