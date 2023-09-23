package stag.ease.stagease.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.repository.AreaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class    AreaService {
    @Autowired
    private AreaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AreaDTO findByNomeArea(String nomeArea) { return modelMapper.map(repository.findByNomeArea(nomeArea), AreaDTO.class); }

    @Transactional
    public List<AreaDTO> findAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, AreaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public AreaDTO create(AreaDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, AreaEntity.class)), AreaDTO.class);
    }

    @Transactional
    public AreaDTO update(Long id, AreaDTO dto) {
        AreaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, AreaEntity.class)), AreaDTO.class);
    }
}
