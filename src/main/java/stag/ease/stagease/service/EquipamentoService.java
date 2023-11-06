package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.entity.EquipamentoEntity;
import stag.ease.stagease.repository.EquipamentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {
    @Autowired
    private EquipamentoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EquipamentoEntity getById(Long id) {
        Optional<EquipamentoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Equipamento não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public EquipamentoEntity getByNomeEquipamento(String nome) {
        return repository.findByNomeEquipamento(nome);
    }

    @Transactional
    public List<EquipamentoEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public EquipamentoEntity create(EquipamentoEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public EquipamentoEntity update(Long id, EquipamentoEntity entity) {
        EquipamentoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipamento não encontrado com o ID: " + id));

        modelMapper.map(entity, existingEntity);

        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
