package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.entity.UBSEntity;
import stag.ease.stagease.repository.UBSRepository;

import java.util.ArrayList;
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
    public List<UBSDTO> getAll() {
        List<UBSDTO> list = new ArrayList<>();
        for (UBSEntity entity : repository.findAll()) {
            UBSDTO map = modelMapper.map(entity, UBSDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public UBSDTO create(UBSDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, UBSEntity.class)), UBSDTO.class);
    }

    @Transactional
    public UBSDTO update(Long id, UBSDTO dto) {
        UBSEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UBS não encontrada com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), UBSDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
