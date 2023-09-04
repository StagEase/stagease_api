package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Repository.AreaRepository;

@Service
public class AreaService {
    @Autowired
    private AreaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AreaDTO create(AreaDTO dto) {
        AreaEntity entity = modelMapper.map(dto, AreaEntity.class);
        AreaDTO retornoDTO = new AreaDTO();
        repository.save(entity);

        return retornoDTO;
    }

    @Transactional
    public AreaDTO update(Long id, AreaDTO dto) {
        AreaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, entity);
        AreaDTO retornoDTO = new AreaDTO();
        repository.save(entity);

        return retornoDTO;
    }
}
