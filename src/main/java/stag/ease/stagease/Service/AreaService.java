package stag.ease.stagease.Service;

import org.springframework.beans.BeanUtils;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Repository.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService {
    @Autowired
    private AreaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AreaDTO findByNomeArea(String nomeArea) {
        AreaEntity entity = repository.findByNomeArea(nomeArea);
        AreaDTO retornoDTO = new AreaDTO();
        BeanUtils.copyProperties(entity, retornoDTO);

        return retornoDTO;
    }

    @Transactional
    public List<AreaDTO> list() {
        List<AreaEntity> entityList = repository.findAll();

        return entityList.stream()
                .map(entity -> modelMapper.map(entity, AreaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public AreaDTO create(AreaDTO dto) {
        AreaEntity entity = modelMapper.map(dto, AreaEntity.class);
        AreaDTO retornoDTO = new AreaDTO();
        repository.save(entity);
        BeanUtils.copyProperties(entity, retornoDTO);

        return retornoDTO;
    }

    @Transactional
    public AreaDTO update(Long id, AreaDTO dto) {
        AreaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, entity);
        AreaDTO retornoDTO = new AreaDTO();
        BeanUtils.copyProperties(repository.save(entity), retornoDTO);

        return retornoDTO;
    }
}
