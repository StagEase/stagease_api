package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.DTO.UBSDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Entity.UBSEntity;
import stag.ease.stagease.Repository.UBSRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UBSService {
    @Autowired
    private UBSRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UBSDTO findByNomeUBS(String nomeUBS) {
        return modelMapper.map(repository.findByNomeUBS(nomeUBS), UBSDTO.class);
    }

    @Transactional
    public List<UBSDTO> findAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, UBSDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UBSDTO create(UBSDTO dto) {
        return modelMapper.map(repository.save(modelMapper.map(dto, UBSEntity.class)), UBSDTO.class);
    }

    @Transactional
    public UBSDTO update(Long id, UBSDTO dto) {
UBSEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));

        return modelMapper.map(repository.save(modelMapper.map(dto, UBSEntity.class)), UBSDTO.class);
    }
}
