package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.DTO.UBSDTO;
import stag.ease.stagease.Entity.UBSEntity;
import stag.ease.stagease.Repository.UBSRepository;

@Service
public class UBSService {
    @Autowired
    private final UBSRepository repository;
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public UBSService(UBSRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UBSEntity create(UBSDTO dto) {
        UBSEntity entity = new UBSEntity();
        modelMapper.map(dto, entity);
        repository.save(entity);

        return entity;
    }

    @Transactional
    public UBSEntity update(Long id, UBSDTO dto) {
        UBSEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, entity);
        repository.save(entity);

        return entity;
    }
}
