package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.repository.AreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class    AreaService {
    @Autowired
    private AreaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AreaDTO getById(Long id) {
        Optional<AreaEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return modelMapper.map(optional.get(), AreaDTO.class);
        } else {
            throw new EntityNotFoundException("Área não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public AreaDTO getByNomeArea(String nome) {
        return modelMapper.map(repository.findByNomeArea(nome), AreaDTO.class);
    }

    @Transactional
    public List<AreaDTO> getAll() {
        List<AreaDTO> list = new ArrayList<>();
        for (AreaEntity entity : repository.findAll()) {
            AreaDTO map = modelMapper.map(entity, AreaDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public AreaDTO create(AreaDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, AreaEntity.class)), AreaDTO.class);
    }

    @Transactional
    public AreaDTO update(Long id, AreaDTO dto) {
        AreaEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área não encontrada com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), AreaDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
