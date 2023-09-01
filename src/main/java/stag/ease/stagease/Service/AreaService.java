package stag.ease.stagease.Service;

import org.springframework.beans.BeanUtils;
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
    private AreaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AreaEntity findByNomeArea(String nomeArea) {
        return repository.findByNomeArea(nomeArea);
    }

    @Transactional
    public AreaDTO create(AreaDTO dto) {
        if (dto.getId() != null) {
            throw new RuntimeException("Não insira o id manualmente");
        }
        AreaEntity entity = modelMapper.map(dto, AreaEntity.class);
        AreaDTO local = new AreaDTO();
        BeanUtils.copyProperties(repository.save(entity), local);
        return local;
    }

    @Transactional
    public AreaDTO update(Long id, AreaDTO dto) {
        AreaEntity entity = this.repository.findById(id).orElse(null);
        if (!entity.getId().equals(dto.getId())) {
            throw new RuntimeException("Não foi possivel encontrar o registro informado");
        }
        modelMapper.map(dto, entity);
        AreaDTO areaDTO = new AreaDTO();
        BeanUtils.copyProperties(repository.save(entity), areaDTO);
        return areaDTO;
    }

    @Transactional
    public void delete(Long id) {
        AreaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o registro informado"));
        repository.delete(entity);
    }
}
