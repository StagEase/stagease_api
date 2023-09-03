package stag.ease.stagease.Service;

import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Repository.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaService {
    @Autowired
    private final AreaRepository repository;
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public AreaService(AreaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public AreaEntity create(AreaDTO dto) {
        AreaEntity entity = new AreaEntity();
        modelMapper.map(dto, entity);
        repository.save(entity);

        return entity;
    }

    @Transactional
    public AreaEntity update(Long id, AreaDTO dto) {
        AreaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, entity);
        repository.save(entity);

        return entity;
    }
}
