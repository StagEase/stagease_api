package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.DTO.UBSDTO;
import stag.ease.stagease.Entity.UBSEntity;
import stag.ease.stagease.Repository.UBSRepository;

@Service
public class UBSService {
    @Autowired
    private UBSRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UBSDTO create(UBSDTO dto) {
        UBSEntity entity = modelMapper.map(dto, UBSEntity.class);
        UBSDTO retornoDTO = new UBSDTO();
        repository.save(entity);

        return retornoDTO;
    }

    @Transactional
    public UBSDTO update(Long id, UBSDTO dto) {
        UBSEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, entity);
        UBSDTO retornoDTO = new UBSDTO();
        repository.save(entity);

        return retornoDTO;
    }
}
